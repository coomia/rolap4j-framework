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

import org.olap4j.CellSet;
import org.rolap4j.exceptions.Rolap4jException;

import java.util.Collection;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
public class Query {

    private Query(QueryBuilder queryBuilder) {

    }

    CellSet executeQuery() throws Rolap4jException {

        return null;
    }

    public static class QueryBuilder implements QueryBasicPlan {

        public QueryBuilder() throws Rolap4jException {
            super();
            throw new Rolap4jException("Not implemented yet !");
        }

        @Override
        public QueryBuilder useColumn(String column) {
            return null;
        }

        @Override
        public QueryBuilder useColumns(Collection<String> columns) {
            return null;
        }

        @Override
        public QueryBuilder useRow(String row) {
            return null;
        }

        @Override
        public QueryBuilder useRows(Collection<String> rows) {
            return null;
        }

        @Override
        public QueryBuilder userFilter(String filter) {
            return null;
        }

        @Override
        public QueryBuilder userFilter(Collection<String> filters) {
            return null;
        }

        @Override
        public Query build() throws Rolap4jException {
            return new Query(this);
        }
    }

}
