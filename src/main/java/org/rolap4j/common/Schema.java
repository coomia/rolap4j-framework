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
package org.rolap4j.common;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andriantomanga on 13/05/16.
 *
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Slf4j
@Getter
public class Schema extends Element {

    // If no name is specified in the mapping file, the following name will be used
    public static final String DEFAULT_NAME = "defaultSchema";

    protected Map<String, Dimension> dimensions = new HashMap<String, Dimension>();

    protected Map<String, Cube> cubes = new HashMap<String, Cube>();

    private void addElement(final Element element, Map elementsMap, final String elementTypeName) {

        // GC : Stop all if the specified element is null
        if (null == element) {
            log.error("The {} to be added to the schema {} cannot be null", elementTypeName, name == null ? "" : name);
            return;
        }

        // GC : Stop all if the name of the specified element is null
        final String elementName = element.getName();
        if (null == elementName) {
            log.error("The {} to be added to the schema {} cannot be null", elementTypeName, name == null ? "" : name);
            return;
        }

        elementsMap.put(elementName, element);
        log.info("The {} {} is added to the schema {}", elementTypeName, elementName, name == null ? "" : name);
    }

    private void removeElement(final Element element, Map elementsMap, final String elementTypeName) {

        // GC : Stop all if the specified element is null
        if (null == element) {
            log.error("The {} to be removed to the schema {} cannot be null", elementTypeName, name == null ? "" : name);
            return;
        }

        // GC : Stop all if the name of the specified element is null
        final String elementName = element.getName();
        if (null == elementName) {
            log.error("The {} to be removed to the schema {} cannot be null", elementTypeName, name == null ? "" : name);
            return;
        }

        elementsMap.remove(elementName);
        log.info("The {} {} is removed from the schema {}", elementTypeName, elementName, name == null ? "" : name);
    }

    private void addElements(Collection<? extends Element> elements, Map elementsMap, final String elementTypeName) {

        for (Element element : elements) {
            addElement(element, elementsMap, elementTypeName);
        }
    }

    private void removeElements(Collection<? extends Element> elements, Map elementsMap, final String elementTypeName) {

        for (Element element : elements) {
            removeElement(element, elementsMap, elementTypeName);
        }
    }

    /**
     * Add and map a dimension in the current schema.
     *
     * @param dimension
     * @see Dimension
     */
    public void addDimension(final Dimension dimension) {

        addElement(dimension, dimensions, ElementType.DIMENSION.getValue());
    }

    /**
     * Add and map some dimensions in the current schema
     *
     * @param someDimensions
     */
    public void addDimensions(Collection<Dimension> someDimensions) {

        addElements(someDimensions, dimensions, ElementType.DIMENSION.getValue());
    }

    /**
     * Remove a dimension from the current schema.
     *
     * @param dimension the dimension to be removed from the current schema.
     * @see Schema
     * @see Dimension
     */
    public void removeDimension(final Dimension dimension) {

        removeElement(dimension, dimensions, ElementType.DIMENSION.getValue());
    }

    /**
     * Remove some dimensions from the current schema
     *
     * @param someDimensions
     * @see Schema
     * @see Dimension
     */
    public void removeDimensions(Collection<Dimension> someDimensions) {

        removeElements(someDimensions, dimensions, ElementType.DIMENSION.getValue());
    }

    /**
     * log all the dimensions of the current schema
     *
     * @see Dimension
     */
    public void logSchemaDimensions() {

        cubes.forEach((dimensionName, dimensions) -> log.info("{}", dimensionName));
    }

    /**
     * Get the specified dimension from the current schema
     *
     * @param dimensionName Name of the dimension to be retrieved
     * @return The instance of {@link Dimension} corresponding of the specified name from the current schema
     * @see Dimension
     */
    public Dimension getDimension(final String dimensionName) {

        return dimensions.get(dimensionName);
    }

    /**
     * Add and map a cube in the current schema.
     *
     * @param cube
     * @see Cube
     */
    public void addCube(final Cube cube) {

        addElement(cube, cubes, ElementType.CUBE.getValue());
    }

    /**
     * Add and map some cubes in the current schema.
     *
     * @param someCubes
     * @see Cube
     */
    public void addCubes(Collection<Cube> someCubes) {

        addElements(someCubes, cubes, ElementType.CUBE.getValue());
    }

    /**
     * Remove a cube from the current schema.
     *
     * @param cube the dimension to be removed from the current schema.
     * @see Cube
     */
    public void removeCube(final Cube cube) {

        removeElement(cube, cubes, ElementType.CUBE.getValue());
    }

    /**
     * Remove some cubes from the current schema.
     *
     * @param someCubes
     * @see Cube
     */
    public void removeCubes(Collection<Cube> someCubes) {

        removeElements(someCubes, cubes, ElementType.CUBE.getValue());
    }

    /**
     * log all the cubes of the current schema
     *
     * @see Cube
     */
    public void logSchemaCubes() {

        cubes.forEach((cubeName, cube) -> log.info("{}", cubeName));
    }

    /**
     * Get the specified cube from the current schema
     *
     * @param cubeName Name of the cube to be retrieved
     * @return The instance of {@link Cube} corresponding of the specified name from the current schema
     * @see Cube
     */
    public Cube getCube(final String cubeName) {

        return cubes.get(cubeName);
    }

    /**
     * Can be used to determine if the specified dimension exists in the current schema
     *
     * @param dimension
     * @return {@code true} if the specified dimension exists in the current schema
     * @see Dimension
     */
    public boolean containsDimension(final Dimension dimension) {

        if (null == dimension) {
            return false;
        }
        return dimensions.containsKey(dimension.getName());
    }

    /**
     * Can be used to determine if the specified dimension exists in the current schema
     *
     * @param dimensionName
     * @return {@code true} if the specified dimension exists in the current schema
     * @see Dimension
     */
    public boolean containsDimension(final String dimensionName) {

        if (null == dimensionName) {
            return false;
        }
        return dimensions.containsKey(dimensionName);
    }


    /**
     * Can be used to determine if the specified cube exists in the current schema
     *
     * @param cube
     * @return {@code true} if the specified cube exists in the current schema
     * @see Cube
     */
    public boolean containsCube(final Cube cube) {

        if (null == cube) {
            return false;
        }
        return cubes.containsKey(cube.getName());
    }

    /**
     * Can be used to determine if the specified cube exists in the current schema
     *
     * @param cubeName
     * @return {@code true} if the specified cube exists in the current schema
     * @see Cube
     */
    public boolean containsCube(final String cubeName) {

        if (null == cubeName) {
            return false;
        }
        return cubes.containsKey(cubeName);
    }
}
