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
package org.rolap4j.utils;

import org.rolap4j.common.Property;
import org.rolap4j.common.PropertyType;

/**
 * @author <a href="mailto:contact@andriantomanga.com">Nabil Andriantomanga</a>
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
public final class PropertyUtil {

    private PropertyUtil() {

        throw new AssertionError();
    }

    public static String getPropertyType(final String type) {

        if (StringUtil.isEmpty(type)) {
            return Property.DEFAULT_TYPE;
        }
        for (PropertyType propertyType : PropertyType.values()) {
            if (propertyType.getValue().equalsIgnoreCase(type)) {
                return propertyType.getValue();
            }
        }
        return Property.DEFAULT_TYPE;
    }
}
