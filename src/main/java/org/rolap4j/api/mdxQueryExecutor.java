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
import org.olap4j.OlapConnection;
import org.rolap4j.connectivity.RolapConnectionProvider;
import org.rolap4j.exceptions.Rolap4jException;

import java.sql.SQLException;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
public final class mdxQueryExecutor {

    public static CellSet executeQuery(final String mdxQuery) throws Rolap4jException {

        try {
            final OlapConnection olapConnection = new RolapConnectionProvider().getConnection().unwrap(OlapConnection.class);
            return olapConnection.createStatement().executeOlapQuery(mdxQuery);
        } catch (SQLException e) {
            throw new Rolap4jException(e.getMessage());
        }
    }

    private mdxQueryExecutor() {
        
        throw new AssertionError();
    }
}
