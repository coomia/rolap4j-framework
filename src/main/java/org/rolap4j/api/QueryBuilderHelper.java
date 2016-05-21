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
package org.rolap4j.api;

import lombok.extern.slf4j.Slf4j;
import org.olap4j.OlapException;
import org.olap4j.mdx.IdentifierNode;
import org.olap4j.mdx.IdentifierSegment;
import org.olap4j.metadata.Cube;
import org.olap4j.query.QueryAxis;
import org.olap4j.query.QueryDimension;
//import org.rolap4j.common.Cube;
import org.olap4j.query.Selection;
import org.rolap4j.common.Dimension;
import org.rolap4j.common.Hierarchy;
import org.rolap4j.common.Level;
import org.rolap4j.common.Schema;
import org.rolap4j.config.Rolap4jConfig;
import org.rolap4j.exceptions.Rolap4jException;
import org.rolap4j.utils.StringUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Slf4j
class QueryBuilderHelper {

    /**
     * Add a new element in the query. An element can be one of the following : dimension, level, property, measure.
     * These elements can be added among column elements, row elements or filter elements
     *
     * @param schema
     * @param cube
     * @param element
     * @param axis
     * @param mdxQuery
     * @throws Rolap4jException
     * @see Schema
     */
    void addCubeElementInQuery(final Schema schema, final Cube cube, final String element,
                               QueryAxis axis, org.olap4j.query.Query mdxQuery, Map<String, String> definedAttributes, Map<String, List<String>> definedListAttributes) throws Rolap4jException

    {

        QueryDimension nextDimension;

        switch (Rolap4jConfig.getType(schema, element)) {

            case DIMENSION:

                nextDimension = mdxQuery.getDimension(element);
                nextDimension.setHierarchizeMode(QueryDimension.HierarchizeMode.PRE);

                if (!axis.getDimensions().contains(nextDimension)) {
                    axis.addDimension(nextDimension);
                }
                if (definedAttributes.containsKey(element) && !definedListAttributes.containsKey(element)) {
                    setLevel(nextDimension, element, definedAttributes.get(element));
                }
                break;

            case LEVEL:

                String dimensionName = getLevelDimensionName(element, schema);
                nextDimension = mdxQuery.getDimension(dimensionName);
                nextDimension.setHierarchizeMode(QueryDimension.HierarchizeMode.PRE);

                if (!axis.getDimensions().contains(nextDimension)) {
                    axis.addDimension(nextDimension);
                }

                if (definedAttributes.containsKey(element)) {
                    try {
                        nextDimension.include(nameList(element, definedAttributes.get(element)));
                    } catch (OlapException e) {
                        throw new Rolap4jException(e.getMessage());
                    }
                } else {
                    setLevel(nextDimension, dimensionName, element);
                }

                break;

            case PROPERTY:
                throw new Rolap4jException("Call to unimplemented feature : property");

            default:

                nextDimension = mdxQuery.getDimension("Measures");
                if (!axis.getDimensions().contains(nextDimension)) {
                    axis.addDimension(nextDimension);
                }

                if (!"Measures".equals(element)) {
                    try {
                        nextDimension.include(IdentifierNode.ofNames("Measures", element).getSegmentList());
                    } catch (OlapException e) {
                        throw new Rolap4jException(e.getMessage());
                    }
                }
                if (definedAttributes.containsKey("Measures") && !definedListAttributes.containsKey(element)) {
                    try {
                        nextDimension.include(IdentifierNode.ofNames("Measures", definedAttributes.get(element)).getSegmentList());
                    } catch (OlapException e) {
                        throw new Rolap4jException(e.getMessage());
                    }
                }
        }
    }

    /**
     * Get the name of dimension containing the specified level
     *
     * @param levelName Name of the level
     * @param schema
     * @return Name of the dimension containing the specified level
     * @see Schema
     */
    public String getLevelDimensionName(final String levelName, final Schema schema) {

        if (StringUtil.isEmpty(levelName) || null == schema) {
            return null;
        }
        for (Map.Entry<String, Dimension> entry : schema.getDimensions().entrySet()) {
            for (Iterator<Hierarchy> hIter = entry.getValue().getHierarchies().iterator(); hIter.hasNext(); ) {
                for (Iterator<Level> lIter = hIter.next().getLevels().iterator(); lIter.hasNext(); ) {
                    if (lIter.next().getName().endsWith(levelName)) {
                        return entry.getKey();
                    }
                }
            }
        }
        return null;
    }


    /**
     * @param nextDimension
     * @param parent
     * @param child
     */
    private void setLevel(QueryDimension nextDimension, String parent, String child) {

        log.debug("[SETLEVEL]: setLevel(dimension : {}, parent : {}, child : {}", nextDimension.getName(), parent, child);

        org.olap4j.metadata.Dimension dimension = nextDimension.getDimension();

        for (org.olap4j.metadata.Hierarchy hierarchy : dimension.getHierarchies()) {
            for (org.olap4j.metadata.Level level : hierarchy.getLevels()) {
                //   log.debug("\t[SETLEVEL]: Comparing {} and {}", level.getUniqueName(), IdentifierNode.ofNames(parent, child).toString());
                if (level.getUniqueName().equals(IdentifierNode.ofNames(parent, child).toString())) {
                    Selection sel = nextDimension.createSelection(level);
                    if (!nextDimension.getInclusions().contains(sel)) {
                        log.debug("\t[SETLEVEL]: {} incluses {}", nextDimension.getName(), level.getName());
                        nextDimension.include(level);
                    }
                }
            }
        }
    }

    /**
     * @param names
     * @return
     */
    public static List<IdentifierSegment> nameList(String... names) {

        return IdentifierNode.ofNames(names).getSegmentList();
    }


}
