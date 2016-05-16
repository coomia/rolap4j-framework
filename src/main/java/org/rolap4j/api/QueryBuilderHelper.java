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
import org.olap4j.metadata.Cube;
import org.olap4j.query.QueryAxis;
import org.olap4j.query.QueryDimension;
//import org.rolap4j.common.Cube;
import org.rolap4j.common.Dimension;
import org.rolap4j.common.Hierarchy;
import org.rolap4j.common.Level;
import org.rolap4j.common.Schema;
import org.rolap4j.config.Rolap4jConfig;
import org.rolap4j.exceptions.Rolap4jException;
import org.rolap4j.utils.StringUtil;

import java.util.Iterator;
import java.util.Map;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Slf4j
public class QueryBuilderHelper {

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
    public void addCubeElementInQuery(final Schema schema, final Cube cube, final String element, QueryAxis axis, org.olap4j.query.Query mdxQuery) throws Rolap4jException {

        QueryDimension nextDimension;

        switch (Rolap4jConfig.getType(schema, element)) {

            case DIMENSION:
                nextDimension = mdxQuery.getDimension(element);
                nextDimension.setHierarchizeMode(QueryDimension.HierarchizeMode.PRE);

                if (!axis.getDimensions().contains(nextDimension)) {
                    axis.addDimension(nextDimension);
                }

                break;

            case LEVEL:
                String dimensionName = getLevelDimensionName(element, schema);
                nextDimension = mdxQuery.getDimension(dimensionName);
                nextDimension.setHierarchizeMode(QueryDimension.HierarchizeMode.PRE);

                if (!axis.getDimensions().contains(nextDimension)) {
                    axis.addDimension(nextDimension);
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
        }
    }

    /**
     * Get the dimension containing the specified level
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
}
