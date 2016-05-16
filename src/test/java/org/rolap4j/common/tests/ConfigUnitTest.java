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

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.rolap4j.common.Schema;
import org.rolap4j.config.DataSource;
import org.rolap4j.config.Rolap4jConfig;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Slf4j
public class ConfigUnitTest {


    @Test
    public void testRolap4jConfiguration() {

        Rolap4jConfig config = Rolap4jConfig.getInstance();
        Schema schema = config.getSchema();

        log.debug("----------------------------------------------------");
        log.debug("Printing data source configuration parameters");
        log.debug("----------------------------------------------------");

        DataSource ds = config.getDataSource();
        log.debug("Catalog : {}", ds.getCatalogPath());
        log.debug("URI : {}", ds.getUri());
        log.debug("Username : {}", ds.getUsername());
        log.debug("Password : {}", ds.getPassword());
        log.debug("Driver class : {}", ds.getDriver());
        log.debug("Show MDX query ? : {}", ds.isMdxVisible() ? "Yes" : "No");


        log.debug("----------------------------------------------------");
        log.debug("Printing all cubes name");
        log.debug("----------------------------------------------------");

        for (String cubeName : schema.getCubes().keySet()) {
            log.debug("\t - {}", cubeName);
        }

        log.debug("----------------------------------------------------");
        log.debug("Printing all dimensions name");
        log.debug("----------------------------------------------------");

        for (String dimensionName : schema.getDimensions().keySet()) {
            log.debug("\t - {}", dimensionName);
        }
    }
}
