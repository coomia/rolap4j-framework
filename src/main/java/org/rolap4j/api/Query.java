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

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.olap4j.Axis;
import org.olap4j.CellSet;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.layout.RectangularCellSetFormatter;
import org.olap4j.metadata.Cube;
import org.olap4j.query.QueryAxis;
import org.olap4j.query.QueryDimension;
import org.olap4j.query.SortOrder;
import org.rolap4j.common.Schema;
import org.rolap4j.config.DataSource;
import org.rolap4j.config.Rolap4jConfig;
import org.rolap4j.connectivity.RolapConnectionProvider;
import org.rolap4j.exceptions.Rolap4jException;
import org.rolap4j.utils.QueryUtil;
import org.rolap4j.utils.StringUtil;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Slf4j
public class Query {

    private org.olap4j.query.Query mdxQuery;

    private String mdx; // used for custom query ...

    private String sql; // used for custom query ...

    @Getter
    private String cubeName; // used cube name ...

    private Query(QueryBuilder queryBuilder) {

        this.cubeName = queryBuilder.cubeName;

        if (StringUtil.isNotEmpty(queryBuilder.mdx)) {
            this.mdx = queryBuilder.mdx.trim();
            return;
        }
        this.mdxQuery = queryBuilder.mdxQuery;
    }

    /**
     * Execute the current query
     *
     * @return The query results wrapped in {@link CellSet} object
     * @throws Rolap4jException An error occurred when executing the query
     */
    public CellSet executeQuery() throws Rolap4jException {

        final String mdxQuery = getMdxQuery();
        log.debug("Executing query {}", Rolap4jConfig.getInstance().getDataSource().isMdxVisible() ? "\n\n" + mdxQuery : "");
        return mdxQueryExecutor.executeQuery(mdxQuery);
    }

    /**
     * @return the MDX expression corresponding to the current query
     */
    private String getMdxQuery() {

        if (StringUtil.isNotEmpty(mdx)) {
            return mdx;
        }

        if (null == mdxQuery) {
            return null;
        }
        return mdxQuery.getSelect().toString();
    }


    /**
     * Print the query results in console
     *
     * @throws Rolap4jException An error occured when trying to execute query in order to print its results
     */
    public void printResults() throws Rolap4jException {

        final RectangularCellSetFormatter formatter = new RectangularCellSetFormatter(false);

        PrintWriter writer = new PrintWriter(System.out);
        formatter.format(executeQuery(), writer);
        writer.flush();

    }


    /**
     * A class builder for {@link Query}.
     * This class implements the <tt>Buider design pattern</tt> to build a safe {@link Query} object. <br />
     * The builder offers a set of methods for creating a specific query
     *
     * @see QueryBasicPlan
     */
    @Slf4j
    public static class QueryBuilder implements QueryBasicPlan {

        private Set<String> columnSets = new LinkedHashSet<String>();

        private Set<String> rowSets = new LinkedHashSet<String>();

        private Set<String> filterSets = new LinkedHashSet<String>();

        private List<String> errors = new ArrayList<String>();

        private Map<String, String> definedAttributes = new HashMap<String, String>();

        private Map<String, List<String>> definedListAttributes = new HashMap<String, List<String>>();

        private Map<Axis, SortOrder> axesSortStrategies = new HashMap<Axis, SortOrder>();

        private String cubeName;

        private Schema schema;

        private DataSource dataSource;

        private org.olap4j.query.Query mdxQuery;

        private org.olap4j.metadata.Cube cube;

        private OlapConnection olapConnection;

        private String mdx;

        private String sql;


        public QueryBuilder() throws Rolap4jException {

            super();
            Rolap4jConfig config = Rolap4jConfig.getInstance();
            schema = config.getSchema();
            dataSource = config.getDataSource();
        }


        /**
         * {@inheritDoc}
         *
         * @param cubeName The name of the concerned cube
         * @return The current instance of {@link QueryBuilder}
         * @throws Rolap4jException
         * @see QueryBuilder
         */
        @Override
        public QueryBuilder fromCube(final String cubeName) throws Rolap4jException {

            this.cubeName = cubeName;
            try {
                olapConnection = new RolapConnectionProvider().getConnection().unwrap(OlapConnection.class);
                cube = olapConnection.getOlapSchema().getCubes().get(cubeName);
            } catch (SQLException e) {
                throw new Rolap4jException(e.getMessage());
            }
            log.info("Selected cube : {}", cubeName);
            return this;
        }

        /**
         * {@inheritDoc}
         *
         * @param column Column to project
         * @return The current instance of {@link QueryBuilder}
         */
        @Override
        public QueryBuilder useColumn(final String column) {

            if (columnSets.contains(column)) {
                errors.add(String.format("The following column is duplicated : %s", column));
                return this;
            }
            if (rowSets.contains(column) || filterSets.contains(column)) {
                errors.add(String.format("The {} column cannot be projected to itself", column));
                return this;
            }
            log.info("New column added : {}", column);
            columnSets.add(column);
            return this;
        }

        /**
         * {@inheritDoc}
         *
         * @param columns Columns to project
         * @return The current instance of {@link QueryBuilder}
         */
        @Override
        public QueryBuilder useColumns(Collection<String> columns) {

            for (Iterator<String> iter = columns.iterator(); iter.hasNext(); ) {
                useColumn(iter.next());
            }
            return this;
        }

        /**
         * {@inheritDoc}
         *
         * @param column Concerned dimension name
         * @param value  Specific element among the elements of the dimension
         * @return The current instance of {@link QueryBuilder}
         */
        @Override
        public QueryBuilder useColumn(String column, String value) {

            useColumn(column);
            definedAttributes.put(column, value);
            return this;
        }

        /**
         * {@inheritDoc}
         *
         * @param row Row to project
         * @return The current instance of {@link QueryBuilder}
         */
        @Override
        public QueryBuilder useRow(final String row) {

            if (rowSets.contains(row)) {
                errors.add(String.format("The following row is duplicated : %s", row));
                return this;
            }
            if (columnSets.contains(row) || filterSets.contains(row)) {
                errors.add(String.format("The {} row cannot be projected to itself", row));
                return this;
            }
            rowSets.add(row);
            log.info("New row added : {}", row);
            return this;
        }


        /**
         * {@inheritDoc}
         *
         * @param rows Rows to project
         * @return The current instance of {@link QueryBuilder}
         */
        @Override
        public QueryBuilder useRows(Collection<String> rows) {

            for (Iterator<String> iter = rows.iterator(); iter.hasNext(); ) {
                useRow(iter.next());
            }
            return this;
        }

        /**
         * {@inheritDoc}
         *
         * @param row   Concerned dimension name
         * @param value Specific element among the elements of the dimension
         * @return The current instance of {@link QueryBuilder}
         */
        @Override
        public QueryBuilder useRow(final String row, final String value) {

            useRow(row);
            definedAttributes.put(row, value);
            return this;
        }

        /**
         * {@inheritDoc}
         *
         * @param filter
         * @return The current instance of {@link QueryBuilder}
         */
        @Override
        public QueryBuilder userFilter(final String filter) {

            if (filterSets.contains(filter)) {
                errors.add(String.format("The following filter is duplicated : %s", filter));
                return this;
            }
            if (columnSets.contains(filter) || rowSets.contains(filter)) {
                errors.add(String.format("The {} filter cannot be projected to itself", filter));
                return this;
            }
            filterSets.add(filter);
            log.info("New filter added : {}", filter);
            return this;
        }

        /**
         * {@inheritDoc}
         *
         * @param filters
         * @return The current instance of {@link QueryBuilder}
         */
        @Override
        public QueryBuilder userFilter(Collection<String> filters) {

            for (Iterator<String> iter = filters.iterator(); iter.hasNext(); ) {
                userFilter(iter.next());
            }
            return this;
        }

        /**
         * {@inheritDoc}
         *
         * @param filter Concerned filter name
         * @param value  Specific element among the elements of the filter
         * @return The current instance of {@link QueryBuilder}
         */
        @Override
        public QueryBuilder useFilter(final String filter, final String value) {

            userFilter(filter);
            definedAttributes.put(filter, value);
            return this;
        }

        /**
         * Set the MultiDimensional eXpressions (MDX) query to execute.
         *
         * @param mdx The MultiDimensional eXpressions (MDX) query to execute
         * @return The current instance of {@link QueryBuilder}
         */
        @Override
        public QueryBuilder withMdx(String mdx) {

            this.mdx = mdx;
            return this;
        }


        /**
         * @return an instance of {@link Query}
         * @throws Rolap4jException
         */
        public Query build() throws Rolap4jException {

            // specific query detected ...
            if (StringUtil.isNotEmpty(mdx)) {
                return new Query(this);
            }

            // check if there is not error ...
            checkErrors();
            // check if the cube is defined in catalog ...
            checkSelectedCubeValidity();
            // check if the projection is OK ...
            checkProjectionValidity();
            // building the query ...
            buildMdxQuery();

            return new Query(this);
        }

        /**
         * @throws Rolap4jException
         */
        private void buildMdxQuery() throws Rolap4jException {

            try {
                mdxQuery = new org.olap4j.query.Query("newQuery", cube);

                final QueryBuilderHelper qbh = new QueryBuilderHelper();

                // Populate columns --------------------------------------------------------------
                QueryAxis columnAxis = mdxQuery.getAxis(Axis.COLUMNS);

                for (Iterator<String> iter = columnSets.iterator(); iter.hasNext(); ) {
                    qbh.addCubeElementInQuery(schema, cube, iter.next(), columnAxis, mdxQuery, definedAttributes, definedListAttributes);
                }

                // Populate rows --------------------------------------------------------------
                QueryAxis rowAxis = mdxQuery.getAxis(Axis.ROWS);

                for (Iterator<String> iter = rowSets.iterator(); iter.hasNext(); ) {
                    qbh.addCubeElementInQuery(schema, cube, iter.next(), rowAxis, mdxQuery, definedAttributes, definedListAttributes);
                }

                // Populate filters --------------------------------------------------------------
                QueryAxis filterAxis = mdxQuery.getAxis(Axis.FILTER);

                for (Iterator<String> iter = filterSets.iterator(); iter.hasNext(); ) {
                    qbh.addCubeElementInQuery(schema, cube, iter.next(), filterAxis, mdxQuery, definedAttributes, definedListAttributes);
                }
                addSortStrategies(axesSortStrategies, mdxQuery);

                mdxQuery.validate();

            } catch (SQLException e) {
                throw new Rolap4jException(e.getMessage());
            }
        }

        /**
         * @param axesSortStrategies
         * @param mdxQuery
         * @throws OlapException
         */
        private void addSortStrategies(Map<Axis, SortOrder> axesSortStrategies, org.olap4j.query.Query mdxQuery) throws OlapException {

            for (Map.Entry<Axis, SortOrder> entry : axesSortStrategies.entrySet()) {
                QueryAxis axis = mdxQuery.getAxis(entry.getKey());
                for (QueryDimension queryDimension : axis.getDimensions()) {
                    queryDimension.clearSort();  // empty all sort strategies
                }
                axis.sort(entry.getValue());
            }

        }


        /**
         * @throws Rolap4jException
         */
        private void checkErrors() throws Rolap4jException {

            if (!errors.isEmpty()) {
                StringBuilder messageBuilder = new StringBuilder("Errors occurred when building query : ");
                for (Iterator<String> iter = errors.iterator(); iter.hasNext(); ) {
                    messageBuilder.append(iter.next()).append("\n");
                }
                throw new Rolap4jException(messageBuilder.toString());
            }
        }

        /**
         * @throws Rolap4jException
         */
        private void checkProjectionValidity() throws Rolap4jException {

            if (columnSets.isEmpty() || rowSets.isEmpty()) {
                throw new Rolap4jException("It is necessary to project at least one column with at least one row");
            }
        }

        /**
         * @throws Rolap4jException
         */
        private void checkSelectedCubeValidity() throws Rolap4jException {

            if (StringUtil.isEmpty(cubeName)) {
                throw new Rolap4jException("Cube name is not specified. Please see the documention");
            }
            if (!schema.containsCube(cubeName)) {
                throw new Rolap4jException("The cube " + cubeName + " is not defined int the following catalog : " + dataSource.getCatalogPath());
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public QueryBuilder sortColumns(org.rolap4j.api.enums.SortOrder order) {

            axesSortStrategies.put(Axis.COLUMNS, QueryUtil.toOlap4jSortOrder(order));
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public QueryBuilder sortRows(org.rolap4j.api.enums.SortOrder order) {

            axesSortStrategies.put(Axis.ROWS, QueryUtil.toOlap4jSortOrder(order));
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public QueryBuilder sortFilters(org.rolap4j.api.enums.SortOrder order) {

            axesSortStrategies.put(Axis.FILTER, QueryUtil.toOlap4jSortOrder(order));
            return this;
        }
    }

}
