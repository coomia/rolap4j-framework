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
public interface QueryBasicPlan {

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
    public Query.QueryBuilder useColumn(final String column);


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
    public Query.QueryBuilder useColumns(Collection<String> columns);

    /**
     * Sets an element determining a horizontal axis of projection. <br />
     *
     * @param row
     * @return A query builder
     * @see org.rolap4j.api.Query.QueryBuilder
     */
    public Query.QueryBuilder useRow(final String row);

    //-----------------------------------------------------------------------------------------------------
    // TODO : write good documentation ...
    /**
     *
     * @param rows
     * @return
     */
    public Query.QueryBuilder useRows(Collection<String> rows);

    public Query.QueryBuilder userFilter(final String filter);

    public Query.QueryBuilder userFilter(Collection<String> filters);

    public Query build() throws Rolap4jException;


}
