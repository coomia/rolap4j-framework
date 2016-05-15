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
package org.rolap4j.helpers;

import lombok.extern.slf4j.Slf4j;
import org.rolap4j.common.ElementType;

import java.util.Set;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Slf4j
public class ElementPropertyValueAvailabilityChecker {

    private ElementPropertyValueAvailabilityChecker() {
        throw new AssertionError();
    }

    /**
     * Check if the property value is available. This method is used to avoid duplication of elements when parsing mapping file.
     *
     * @param value
     * @param notAvailableValues
     * @param elementType
     * @param propertyName
     * @return {@true } if the value is still available
     * @see org.rolap4j.parsers.Dom4jBasedCatalogParser
     */
    public static final boolean isAvailable(final String value, Set<String> notAvailableValues, final ElementType elementType, final String propertyName) {

        if (notAvailableValues.contains(value)) {

            log.debug("The {} {} must be unique ", elementType == null ? "element" : elementType.getValue(), propertyName);
            return false;
        }
        notAvailableValues.add(value);
        return true;
    }
}
