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
package org.rolap4j.connectivity;

import lombok.extern.slf4j.Slf4j;
import org.rolap4j.config.DataSource;
import org.rolap4j.config.Rolap4jConfig;
import org.rolap4j.exceptions.Rolap4jException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Slf4j
public class RolapConnectionProvider implements ConnectionProvider {


    @Override
    public Connection getConnection() throws Rolap4jException {

        final DataSource dataSource = Rolap4jConfig.getInstance().getDataSource();
        try {
            Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
        } catch (ClassNotFoundException e) {
            throw new Rolap4jException(e.getMessage());
        }

        StringBuilder olapURLConnection = new StringBuilder();

        olapURLConnection.append("jdbc:mondrian:").append("Jdbc=")
                .append(dataSource.getUri()).append(";").append("JdbcUser=")
                .append(dataSource.getUsername()).append(";JdbcPassword=")
                .append(dataSource.getPassword()).append(";")
                .append("Catalog=file:").append(dataSource.getCatalogPath())
                .append(";").append("JdbcDrivers=")
                .append(dataSource.getDriver()).append(";");

        log.debug("Connecting to {} with the user {} ...", dataSource.getUri(), dataSource.getUsername());
        try {
            return DriverManager.getConnection(olapURLConnection.toString());
        } catch (SQLException e) {
            throw new Rolap4jException(e.getMessage());
        }
    }
}
