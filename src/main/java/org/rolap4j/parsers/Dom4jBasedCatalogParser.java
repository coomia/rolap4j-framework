/*
 * Licensed to Rolap4J Framework under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Rolap4J Framework licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.rolap4j.parsers;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Node;
import org.rolap4j.common.*;
import org.rolap4j.exceptions.FileNotFoundException;
import org.rolap4j.exceptions.ParsingException;
import org.rolap4j.helpers.ElementPropertyValueAvailabilityChecker;
import org.rolap4j.utils.PropertyUtil;
import org.rolap4j.utils.StringUtil;
import org.rolap4j.utils.XmlDocumentUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>This is an implementation of {@link CatalogParser} that parses the catalog (mapping file) that describes
 * the data-warehouse structure.This implementation is <a href="">Doma4j</a>  based parser.</p>
 *
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @see CatalogParser
 * @since 1.0-RELEASE
 */
@Slf4j
public class Dom4jBasedCatalogParser extends AbstractCatalogParser {

    /**
     * @param catalogFilePath path to the catalog file (please see the documention)
     */
    public Dom4jBasedCatalogParser(String catalogFilePath) {
        super(catalogFilePath);
    }

    /**
     * {@inheritDoc}. <br>
     * The example bellow shows how to get an instance of {@link Schema} from an existing catalog : <br>
     * <pre>
     *
     *     {@code
     *          CatalogParser parser = new Dom4jBasedCatalogParser("my-data-warehouse-mapping.xml");
     *          // can use another implementation
     *          try {
     *              Schema schema = parser.parseCatalog();
     *              // use schema to reach cubes, dimensions, hierarchies, levels ...
     *          } catch(Rolap4jException eX) {
     *              eX.printStackTrace();
     *          }
     *     }
     * </pre>
     *
     * @return A {@link Schema} corresponding to the filePath
     * @throws FileNotFoundException The specified file was not found
     * @throws ParsingException      An error occurred when parsing the file
     */
    @Override
    public Schema parseCatalog() throws FileNotFoundException, ParsingException {

        final Document document = createDocument();

        Node schemaNode = document.selectSingleNode("//Schema");
        if (null == schemaNode) {
            throw new ParsingException("The root element (Schema) was not found.");
        }

        final String schemaName = schemaNode.valueOf("@name");
        final Schema schema = new Schema();
        schema.setName(StringUtil.isEmpty(schemaName) ? Schema.DEFAULT_NAME : schemaName);

        // Loading dimensions ...
        schema.addDimensions(loadDimensions(document));

        // Loading cubes ...
        schema.addCubes(loadCubes(document));

        return schema;
    } // end parseCatalog( ...

    /**
     * Get all cubes mapped in the data-warehouse mapping file
     *
     * @param document An instance of {@link Document}
     * @return
     * @see Cube
     */
    // TODO : table, dimensionUsage, Measures
    private List<Cube> loadCubes(final Document document) throws ParsingException {

        log.debug("Loading all cubes from the schema ...");
        List<Cube> cubes = new ArrayList<>();

        List<Node> cubeNodes = document.selectNodes("//Schema/Cube");
        Set<String> notAvailableCubeNames = new HashSet<>();
        String worker;
        int sprinter = 1;
        for (Node cubeNode : cubeNodes) {
            Cube cube = new Cube();

            worker = cubeNode.valueOf("@name");
            if (StringUtil.isEmpty(worker)) {
                throw new ParsingException("The cube number {} must have a name attribute defined" + sprinter);
            }

            checkCubeAvailability(worker, notAvailableCubeNames);
            cube.setName(worker);

            // table
            //worker = cubeNode.selectSingleNode("Table");

            // DimensionUsage

            // Measure

            cubes.add(cube);

            log.debug("\t{} cube was found", cube.getName());
            sprinter++;
        }
        return cubes;
    }

    /**
     * Check if the cube name is still available
     *
     * @param cubeName
     * @param notAvailableCubeNames
     * @throws ParsingException
     */
    private void checkCubeAvailability(final String cubeName, Set<String> notAvailableCubeNames) throws ParsingException {

        if (!ElementPropertyValueAvailabilityChecker.isAvailable(cubeName, notAvailableCubeNames, ElementType.CUBE, "name")) {
            throw new ParsingException("The cube name must be unique");
        }
    }


    /**
     * Get all dimensions mapped in the data-warehouse mapping file
     *
     * @param document An instance of {@link Document}
     * @return All dimensions
     * @throws ParsingException
     * @see Cube
     */
    private List<Dimension> loadDimensions(final Document document) throws ParsingException {

        log.debug("Loading all dimensions from the schema ...");
        List<Dimension> dimensions = new ArrayList<>();

        List<Node> dimensionNodes = document.selectNodes("//Schema/Dimension");
        Set<String> notAvailableDimensionNames = new HashSet<>();
        int sprinter = 1;
        String dimensionName;

        for (Node node : dimensionNodes) {
            Dimension dimension = new Dimension();
            dimensionName = node.valueOf("@name");

            if (StringUtil.isEmpty(dimensionName)) {
                throw new ParsingException("A dimension name is missing. The dimension index is : " + sprinter);
            }

            checkDimensionAvailability(dimensionName, notAvailableDimensionNames);
            dimension.setName(dimensionName);
            dimension.addHierarchies(loadHierarchies(node, dimensionName));
            dimensions.add(dimension);

            log.debug("\t{} dimension was found", dimension.getName());
            sprinter++;
        }

        log.debug("{} dimension(s) successfully loaded", (sprinter - 1));
        return dimensions;
    } // end loadDimensions( ...

    /**
     * Load the current dimension hierachies
     *
     * @param dimensionNode
     * @param dimensionName
     * @return list of hierarchies that correspond to the dimension
     * @throws ParsingException an error occurred when parsing the file
     */
    private List<Hierarchy> loadHierarchies(final Node dimensionNode, final String dimensionName)
            throws ParsingException {

        log.debug("Loading all {} hierarchies", dimensionName);
        List<Node> hierarchiesNodes = dimensionNode.selectNodes("Hierarchy");
        List<Hierarchy> hierarchies = new ArrayList<>();
        Set<String> notAvailableHierarchyNames = new HashSet<>();
        String worker;
        int sprinter = 1;
        boolean defaultHierarchyDefined = false;

        for (Node hierarchyNode : hierarchiesNodes) {

            Hierarchy hierarchy = new Hierarchy();

            worker = hierarchyNode.valueOf("@hasAll");
            checkHasAllValidity(worker, dimensionName, sprinter);
            hierarchy.setHasAll("true".equalsIgnoreCase(worker));

            // get the required name ...
            worker = hierarchyNode.valueOf("@name");
            if (StringUtil.isEmpty(worker) && !defaultHierarchyDefined) {
                throw new ParsingException("An hierarchy name is missing. The hierarchy index is : " + sprinter);
            }
            defaultHierarchyDefined = true;

            // set the name if available ...
            checkHiearchyAvailability(worker, notAvailableHierarchyNames);
            hierarchy.setName(worker);

            // set the hierarchy primary key attribute ...
            worker = hierarchyNode.valueOf("@primaryKey");
            if (StringUtil.isEmpty(worker)) {
                throw new ParsingException("The following attribute must be specified for the " +
                        dimensionName + " dimension : primaryKey");
            }
            hierarchy.setPrimaryKey(worker);
            hierarchy.addLevels(loadLevels(hierarchyNode, hierarchy.getName(), dimensionName));

            hierarchies.add(hierarchy);
            log.debug("\tHierarchy {} added to {} hierarchies", dimensionName);
        }

        return hierarchies;
    } // end loadHierarchies( ...

    /**
     * Load all levels from an hierarchy node
     *
     * @param hierarchyNode The hierarchy node
     * @param hierarchyName The name of the concerned hierarchy
     * @param dimensionName The name of the concerned dimension
     * @return All the levels of the specified hierarchy
     * @throws ParsingException
     * @see Level
     */
    private List<Level> loadLevels(final Node hierarchyNode, final String hierarchyName, final String dimensionName)
            throws ParsingException {

        log.debug("Loading all {} levels ...", hierarchyName);
        List<Level> levels = new ArrayList<>();
        List<Node> levelNodes = hierarchyNode.selectNodes("Level");

        String worker;
        int sprinter = 1;
        for (Node levelNode : levelNodes) {
            Level level = new Level();

            worker = levelNode.valueOf("@name");
            if (StringUtil.isEmpty(worker)) {

                StringBuilder exceptionMessage = new StringBuilder();

                exceptionMessage.append("The name attribute is missing for the #").append(sprinter)
                        .append(" level of the ").append(hierarchyName).append(" hierarchy of the ")
                        .append(dimensionName).append(" dimension ");

                throw new ParsingException(exceptionMessage.toString());
            }
            level.setName(worker);

            worker = levelNode.valueOf("@column");
            if (StringUtil.isEmpty(worker)) {
                throw new ParsingException("A column attribute is required for the " + level.getName());
            }
            level.setColumn(worker);

            worker = levelNode.valueOf("@uniqueMembers");
            checkUniqueMembersValidity(worker, level.getName());
            level.setUniqueMembers("true".equalsIgnoreCase(worker));

            level.addProperties(loadProperties(levelNode, level.getName(), dimensionName));
            log.debug("\tLevel {} added to {} hierarchy", level.getName(), hierarchyName);
            sprinter++;
        }
        return levels;

    }

    /**
     * @param levelNode
     * @param levelName
     * @param dimensionName
     * @return
     * @throws ParsingException
     */
    private Set<Property> loadProperties(final Node levelNode, final String levelName, final String dimensionName) throws ParsingException {

        log.debug("Loading all {} level properties ...", levelName);
        Set<Property> properties = new HashSet<>();
        List<Node> propertyNodes = levelNode.selectNodes("Property");
        String worker;
        Set<String> notAvailablePropertyNames = new HashSet<>();

        for (Node propertyNode : propertyNodes) {

            Property property = new Property();
            worker = propertyNode.valueOf("@name");

            if (StringUtil.isEmpty(worker)) {
                throw new ParsingException("The name attribute is missing for one or some properties of " + levelName + " level");
            }

            checkPropertyAvailability(worker, notAvailablePropertyNames);
            property.setName(worker);
            worker = propertyNode.valueOf("@column");

            if (StringUtil.isEmpty(worker)) {
                throw new ParsingException("A column attribute is required for " + property.getName() + " property of " + levelName + " level");
            }

            property.setColumn(worker);
            property.setType(PropertyUtil.getPropertyType(propertyNode.valueOf("@type")));
            properties.add(property);
        }
        return properties;
    }


    /**
     * Check if the property name is still available
     *
     * @param propertyName
     * @param notAvailablePropertyNames
     * @throws ParsingException
     */
    private void checkPropertyAvailability(final String propertyName, Set<String> notAvailablePropertyNames) throws ParsingException {

        if (!ElementPropertyValueAvailabilityChecker.isAvailable(propertyName, notAvailablePropertyNames, ElementType.PROPERTY,
                "name")) {
            throw new ParsingException("The property name must be unique");
        }

    }

    /**
     * @param uniqueMembersValue
     * @param levelName
     * @throws ParsingException
     */
    private void checkUniqueMembersValidity(String uniqueMembersValue, final String levelName) throws ParsingException {

        if (StringUtil.isEmpty(uniqueMembersValue)) {

            throw new ParsingException("The uniqueMembers attribute is required for the " + levelName + " level");
        }

        if ("true".equalsIgnoreCase(uniqueMembersValue) || "false".equalsIgnoreCase(uniqueMembersValue)) {

            return; // valid hasAll property ...
        }

        throw new ParsingException("The value of the uniqueMembers attribute must be one of the following : 'true' or 'false'");
    }

    /**
     * Check if the cube hierarchy is still available
     *
     * @param hierarchyName
     * @param notAvailableHierarchyNames
     * @throws ParsingException
     */
    private void checkHiearchyAvailability(final String hierarchyName, Set<String> notAvailableHierarchyNames)
            throws ParsingException {

        if (!ElementPropertyValueAvailabilityChecker.isAvailable(hierarchyName, notAvailableHierarchyNames, ElementType.HIERARCHY,
                "name")) {
            throw new ParsingException("The hierarchy name must be unique");
        }
    }

    /**
     * Check if the 'hasAll' property is valid
     *
     * @param hasAll        value to be checked
     * @param dimensionName concerned dimension name
     * @param index         index of the dimension in the schema
     * @throws ParsingException an error occurred when parsing the file
     */
    private void checkHasAllValidity(final String hasAll, final String dimensionName, int index)
            throws ParsingException {

        if (StringUtil.isEmpty(hasAll)) {
            // log before throwing the exception ...
            log.error("The hasAll property is required for the {} dimension", dimensionName);

            throw new ParsingException("The hasAll property is required for the " + dimensionName + " dimension. #" + index);
        }

        if ("true".equalsIgnoreCase(hasAll) || "false".equalsIgnoreCase(hasAll)) {
            return; // valid hasAll property ...
        }

        final StringBuilder exceptionMessage = new StringBuilder();
        exceptionMessage.append("Expected value of the 'hasAll' property of ").append(dimensionName)
                .append(" element is one of the following :");
        exceptionMessage.append(" (true, false), but the following value was found : ").append(hasAll);


        throw new ParsingException(exceptionMessage.toString());
    }


    /**
     * @return an instance of {@link Document} corresponding to the file to be parsed
     * @throws FileNotFoundException The file to be parsed is not found
     * @throws ParsingException      An error occured when parsing the file
     */
    private Document createDocument() throws FileNotFoundException, ParsingException {

        return XmlDocumentUtil.createDocumentFromXml(this.catalogFilePath);
    }

    /**
     * Check if the dimension name is still available
     *
     * @param dimensionName
     * @param notAvailableDimensionNames
     * @throws ParsingException
     */
    private void checkDimensionAvailability(String dimensionName, Set<String> notAvailableDimensionNames)
            throws ParsingException {

        if (!ElementPropertyValueAvailabilityChecker.isAvailable(dimensionName, notAvailableDimensionNames, ElementType.DIMENSION,
                "name")) {
            throw new ParsingException("The property name must be unique");
        }

    }
}
