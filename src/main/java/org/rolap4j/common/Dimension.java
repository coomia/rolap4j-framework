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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by andriantomanga on 12/05/16.
 *
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Data
@EqualsAndHashCode(exclude = {"hierarchies"})
@ToString(exclude = {"hierarchies"}, callSuper = true)
public class Dimension {

    protected List<Hierarchy> hierarchies = new ArrayList<>();

    protected String name;

    public void addHierarchy(final Hierarchy hierarchy) {

        hierarchies.add(hierarchy);
    }

    public void removeHierarchy(final Hierarchy hierarchy) {

        hierarchies.remove(hierarchy);
    }

    public boolean hasHierarchy(final Hierarchy hierarchy) {

        return hierarchies.contains(hierarchy);
    }

    public void addHierarchies(Collection<Hierarchy> someHierarchies) {

        hierarchies.addAll(someHierarchies);
    }

}
