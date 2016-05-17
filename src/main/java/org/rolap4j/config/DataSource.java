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
package org.rolap4j.config;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.rolap4j.config.enums.DriverKeyClassMap;
import org.rolap4j.exceptions.ConfigurationException;
import org.rolap4j.utils.FileUtil;
import org.rolap4j.utils.StringUtil;

import java.io.File;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Data
public class DataSource {

    private String uri;

    private String username;

    private String password;

    private String driver;

    private String catalogPath;

    private boolean mdxVisible;

    private DataSource(DataSourceBuilder builder) {

        this.uri = builder.uri;
        this.username = builder.username;
        this.password = builder.password;
        this.driver = builder.driver;
        this.catalogPath = builder.catalogPath;
        this.mdxVisible = builder.mdxVisible;
    }

    @Slf4j
    public static class DataSourceBuilder {
        private String uri;

        private String username;

        private String password;

        private String driver;

        private String catalogPath;

        private boolean mdxVisible;

        public DataSourceBuilder withUsername(final String username) {

            this.username = username;
            return this;
        }

        public DataSourceBuilder withPassword(final String password) {

            this.password = password;
            return this;
        }

        public DataSourceBuilder withUri(final String uri) {

            this.uri = uri;
            return this;
        }

        public DataSourceBuilder withDriver(final String driver) {

            this.driver = driver;
            return this;
        }

        public DataSourceBuilder withCatalog(final String catalogPath) {

            this.catalogPath = catalogPath;
            return this;
        }

        public DataSourceBuilder withMdxVisible(final String mdxVisibility) {

            if (StringUtil.isEmpty(mdxVisibility)) {
                return this;
            }

            mdxVisible = "true".equalsIgnoreCase(mdxVisibility.trim());
            return this;
        }

        public DataSource build() throws ConfigurationException {

            boolean allIsOk = true;
            StringBuilder messageBuilder = new StringBuilder();
            if (StringUtil.isEmpty(username)) {
                messageBuilder.append("\n\tUsername is required for datasource");
                allIsOk = false;
            }

//            if (StringUtil.isEmpty(password)) {
//                messageBuilder.append("\n\tPassword is required for datasource");
//                allIsOk = false;
//            }

            if (StringUtil.isEmpty(uri)) {
                messageBuilder.append("\n\tUri is required for datasource");
                allIsOk = false;
            }

            if (StringUtil.isEmpty(catalogPath)) {
                messageBuilder.append("\n\tPath to catalog file is required for datasource");
                allIsOk = false;
            } else if (!FileUtil.fileExists(catalogPath)) {
                messageBuilder.append("\n\tRolap4j cannot locate the following catalog file : ").append(catalogPath);
                allIsOk = false;
            }

            if (StringUtil.isEmpty(driver) && allIsOk) {
                driver = getDriverFromUri(uri);
                if (driver == null) {
                    messageBuilder.append("\n\tDriver not found to connect to the datasource");
                    allIsOk = false;
                } else {
                    log.info("Rolap4j will use the following driver to communicate with data source : {}", driver);
                }
            }

            if (!allIsOk) {
                throw new ConfigurationException(messageBuilder.toString());
            }
            return new DataSource(this);
        }

        /**
         * @param uri Data source URI
         * @return Driver class corresponding to the datasource uri
         */
        private String getDriverFromUri(final String uri) {

            if (StringUtil.isEmpty(uri)) {
                return null;
            }

            for (DriverKeyClassMap driver : DriverKeyClassMap.values()) {
                if (uri.toLowerCase().contains(driver.getKey())) {
                    return driver.getClassCanonicalName();
                }
            }
            return null;
        }
    }
}
