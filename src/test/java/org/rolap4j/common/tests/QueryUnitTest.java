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
package org.rolap4j.common.tests;

import org.junit.Ignore;
import org.junit.Test;
import org.olap4j.CellSet;
import org.rolap4j.api.Query;
import org.rolap4j.exceptions.Rolap4jException;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
public class QueryUnitTest {

    @Ignore
    @Test
    public void executeQueryTest1() {

        Query query = null;
        try {

            query = new Query.QueryBuilder().fromCube("Ventes").useColumn("Temps").useRow("Lieu").build();

            query.printResults();

        } catch (Rolap4jException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void executeQueryTest2() {

        try {

            // Creating a MDX query
            StringBuilder mdx = new StringBuilder();

            mdx.append("SELECT");
            mdx.append("{Hierarchize({[Produit].[Ordinateur]})} ON COLUMNS, ");
            mdx.append("{Hierarchize({[Lieu].[Tous les lieux]})} ON ROWS ");
            mdx.append("FROM [Ventes] ");

            // Setting the query ...
            Query query = new Query.QueryBuilder().setMdx(mdx.toString()).build();

            // Just printing the results ...
            query.printResults();

            // Retrieving the results in iterable object ...
            CellSet results = query.executeQuery();

        } catch (Rolap4jException e) {
            e.printStackTrace();
        }

    }
}
