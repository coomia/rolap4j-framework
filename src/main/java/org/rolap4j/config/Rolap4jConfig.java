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

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.rolap4j.common.*;
import org.rolap4j.exceptions.ConfigurationException;
import org.rolap4j.exceptions.Rolap4jException;
import org.rolap4j.parsers.CatalogParser;
import org.rolap4j.parsers.Dom4jBasedCatalogParser;
import org.rolap4j.utils.PropertiesUtil;
import org.rolap4j.utils.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Slf4j
public final class Rolap4jConfig /* @singleton */ {

    public static final String DEFAULT_CONFIGURATION_FILE = "datawarehouse.properties";

    /**
     *
     */
    public static final String ROLAP4J_HOME_ENV_VAR_NAME = "ROLAP4J_HOME";

    @Getter
    @Setter
    private String configurationFilePath = DEFAULT_CONFIGURATION_FILE;

    @Getter
    private DataSource dataSource;

    private CatalogParser catalogParser;

    @Getter
    private Schema schema;

    private static class Rolap4jConfigHolder {
        private static final Rolap4jConfig INSTANCE = new Rolap4jConfig();

        static {
            try {
                INSTANCE.configure();
            } catch (Rolap4jException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the unique instance of {@link Rolap4jConfig}. This object give the access to the following elements :
     * <ul>
     * <li>Data source : use the config object to access to all informations about the data source</li>
     * <li>Data warehouse structure : use the config object to access to cubes, dimensions, hierarchies ...</li>
     * </ul>
     * <p>
     * The following example shows how to print all dimensions name.
     * </p>
     * <pre>
     *     {@code
     *          Rolap4jConfig config = Rolap4jConfig.getInstance();
     *          for (String cubeName : config.getSchema().getCubes().keySet()) {
     *
     *              System.out.println(cubeName);
     *          }
     *     }
     * </pre>
     *
     * @return the unique instance of {@link Rolap4jConfig}
     */
    public static Rolap4jConfig getInstance() {
        return Rolap4jConfigHolder.INSTANCE;
    }

    private Rolap4jConfig() {
    }

    /**
     * @throws Rolap4jException
     */
    private void configure() throws Rolap4jException {

        Properties rolapProperties = loadRolapProperties();

        dataSource = new DataSource.DataSourceBuilder().withUsername(rolapProperties.getProperty("username")).withPassword(rolapProperties.getProperty("password")).withUri(rolapProperties.getProperty("uri")).withDriver(rolapProperties.getProperty("driver")).withCatalog(PropertiesUtil.resolveEnvVariable(rolapProperties.getProperty("catalog"))).withMdxVisible(rolapProperties.getProperty("show_mdx")).build();

        catalogParser = new Dom4jBasedCatalogParser(dataSource.getCatalogPath());
        schema = catalogParser.parseCatalog();
    }

    /**
     * @return
     * @throws ConfigurationException
     */
    private Properties loadRolapProperties() throws ConfigurationException {

        Properties rolapProperties = new Properties();
        try {
            // ROLAP4J_HOME_ENV_VAR_NAME
            configurationFilePath = System.getenv(ROLAP4J_HOME_ENV_VAR_NAME);
            if (StringUtil.isEmpty(configurationFilePath)) {
                configurationFilePath = DEFAULT_CONFIGURATION_FILE;
            } else {
                configurationFilePath += File.separator + DEFAULT_CONFIGURATION_FILE;
            }
            log.debug("Defined configuration file : {}", configurationFilePath);

            rolapProperties.load(new FileInputStream(configurationFilePath));
        } catch (IOException exceptionOne) {
            InputStream inputStream = Rolap4jConfig.class.getClassLoader().getResourceAsStream(configurationFilePath);
            log.debug("Loading data warehouse configuration in web context");
            try {
                rolapProperties.load(inputStream);
            } catch (Exception exceptionTwo) {
                File configFile = new File(configurationFilePath);
                try {
                    if (configFile.createNewFile()) { // create an empty file if not exists ...
                        log.debug("Data warehouse configuration parameters must be specified in the following file {}", configurationFilePath);
                    } else {
                        log.debug("Unable to create the following file {}", configurationFilePath);
                    }
                } catch (IOException exceptionThree) {
                    // do nothing ...
                }
                throw new ConfigurationException(exceptionTwo.getMessage());
            }
        }
        return rolapProperties;
    }

    /**
     * Get the type of the element which name is given. Element type must be one of the following :
     * Cube, measure, dimension, hierarchy, level and property.
     *
     * @param schema
     * @param element
     * @return The type of the element which name is given
     * @see ElementType
     * @see Schema
     */
    public static ElementType getType(Schema schema, String element) {

        if (StringUtil.isEmpty(element)) {
            return null;
        }
        element = element.trim();
        for (Map.Entry<String, org.rolap4j.common.Cube> entry : schema.getCubes().entrySet()) {
            org.rolap4j.common.Cube cube = entry.getValue();
            if (element.equalsIgnoreCase(cube.getName())) {
                return ElementType.CUBE;
            }
            for (Iterator<Measure> measureIterator = cube.getMeasures().iterator(); measureIterator.hasNext(); ) {
                if (element.equalsIgnoreCase(measureIterator.next().getName())) {
                    return ElementType.MEASURE;
                }
            }
        }
        for (Map.Entry<String, Dimension> entry : schema.getDimensions().entrySet()) {
            Dimension dimension = entry.getValue();
            if (element.equalsIgnoreCase(entry.getKey())) {
                return ElementType.DIMENSION;
            }
            for (Iterator<Hierarchy> hIter = dimension.getHierarchies().iterator(); hIter.hasNext(); ) {
                Hierarchy hierarchy = hIter.next();
                if (element.equalsIgnoreCase(hierarchy.getName())) {
                    return ElementType.HIERARCHY;
                }
                for (Iterator<Level> lIter = hierarchy.getLevels().iterator(); lIter.hasNext(); ) {
                    Level level = lIter.next();
                    if (element.equalsIgnoreCase(level.getName())) {
                        return ElementType.LEVEL;
                    }
                    for (Iterator<Property> pIter = level.getProperties().iterator(); pIter.hasNext(); ) {
                        if (element.equalsIgnoreCase(pIter.next().getName())) {
                            return ElementType.PROPERTY;
                        }
                    }
                }
            }
        }
        return ElementType.UNKNOWN;
    }

}
