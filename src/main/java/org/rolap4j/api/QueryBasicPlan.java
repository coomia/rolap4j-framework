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

import org.rolap4j.exceptions.Rolap4jException;

import java.util.Collection;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
interface QueryBasicPlan {


    /**
     * This method allow you to connect to your data warehouse and select a cube with which you want to work
     *
     * @param cube Name of the cube with which we want to work
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     * @see org.rolap4j.common.Cube
     */
    Query.QueryBuilder fromCube(final String cube) throws Rolap4jException;

    /**
     * Sets an element determining a vertical axis of projection. <br />
     * The following code projects on column axe the names and types of providers on monthly purchases :
     * <pre>
     *
     *     {@code
     *
     * 	        Query myQuery = new QueryBuilder().fromCube("Sales")
     * 		            .useColumn("Provider_name").useColumn("Provider_type")
     * 		            .useRow("Month")
     * 	            .build();
     *     }
     * </pre>
     *
     * @param column
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     */
    Query.QueryBuilder useColumn(final String column);


    /**
     * Sets an element determining a vertical axis of projection. <br />
     * The following code projects on column axe the names and types of providers on monthly purchases :
     * <pre>
     *
     *     {@code
     *          List<String> columns = Arrays.asList("Provider_name", "Provider_type");
     *
     * 	        Query myQuery = new QueryBuilder().fromCube("Sales")
     * 		            .useColumns(columns)
     * 		            .useRow("Month")
     * 	            .build();
     *     }
     * @param columns
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     */
    Query.QueryBuilder useColumns(Collection<String> columns);

    /**
     * Set an element determining a vertical axis of projection by defining its value.
     * The following example shows how to project a particular product within the query :
     * <pre>
     *     {@code
     *          Query myQuery = new QueryBuilder().fromCube("Sales")
     *              .useColumns("Product", "Macbook") // project only the "Macbook" product
     *              .useRow("Month")
     *          .build();
     *     }
     * </pre>
     *
     * @param column Concerned dimension name
     * @param value  Specific element among the elements of the dimension
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     */
    Query.QueryBuilder useColumn(String column, String value);

    /**
     * Sets an element determining a horizontal axis of projection. <br />
     *
     * @param row
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     */
    Query.QueryBuilder useRow(final String row);

    //-----------------------------------------------------------------------------------------------------
    // TODO : write good documentation ...

    /**
     * @param rows
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     */
    Query.QueryBuilder useRows(Collection<String> rows);

    /**
     * Set an element determining a horizontal axis of projection by defining its value.
     * The following example shows how to project a particular product within the query :
     * <pre>
     *     {@code
     *          Query myQuery = new QueryBuilder().fromCube("Sales")
     *              .useColumn("Month")
     *              .useRow("Product", "Macbook") // project only the "Macbook" product
     *          .build();
     *     }
     * </pre>
     *
     * @param row   Concerned dimension name
     * @param value Specific element among the elements of the dimension
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     */
    Query.QueryBuilder useRow(String row, String value);

    /**
     * @param filter
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     */
    Query.QueryBuilder userFilter(final String filter);

    /**
     * @param filters
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     */
    Query.QueryBuilder userFilter(Collection<String> filters);


    /**
     * Set an element determining a filter axis of projection by defining its value.
     *
     * @param filter Concerned dimension name
     * @param value  Specific element among the elements of the dimension
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     */
    Query.QueryBuilder useFilter(String filter, String value);

    /**
     * Set the MultiDimensional eXpressions (MDX) query to execute.
     * For more informations, please see : <a href="https://en.wikipedia.org/wiki/MultiDimensional_eXpressions">the wiki</a>
     *
     * @param mdxQuery
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     */
    Query.QueryBuilder withMdx(final String mdxQuery);


}
