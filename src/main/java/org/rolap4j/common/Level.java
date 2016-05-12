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
package org.rolap4j.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andriantomanga on 11/05/16.
 *
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@ToString(exclude = {"properties"}, callSuper = true)
@EqualsAndHashCode(exclude = {"properties"})
@Data
@Slf4j
public class Level extends MappingElement {

    private boolean uniqueMembers;

    private Set<Property> properties = new HashSet<>();

    /**
     * Add a new property to the level element
     *
     * @param property
     */
    public void addProperty(Property property) {

        log.debug("Adding a new property {} to the level {}", property, this);
        properties.add(property);
    }

    /**
     * Remove the property from the level element
     *
     * @param property
     */
    public void removeProperty(Property property) {

        log.debug("Removing the property {} from the level {}", property, this);
        properties.remove(property);
    }

    /**
     * Add some properties to the level element
     *
     * @param someProperties
     */
    public void addAllProperties(Set<Property> someProperties) {

        log.debug("Adding some properties to the level {}", this);
        properties.addAll(someProperties);
    }

    /**
     * Add some properties to the level element
     *
     * @param someProperties
     */
    public void addAllProperties(List<Property> someProperties) {

        log.debug("Adding some properties to the level {}", this);
        properties.addAll(someProperties);
    }

    /**
     * Remove all properties of the level element
     */
    public void clearProperties() {

        log.debug("Removing all properties from the level {}", this);
        properties.clear();
    }
}
