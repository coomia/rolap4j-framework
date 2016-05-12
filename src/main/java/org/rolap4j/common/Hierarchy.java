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
@EqualsAndHashCode(exclude = {"levels"})
@ToString(exclude = {"levels"}, callSuper = true)
@Slf4j
public class Hierarchy {

    /**
     * Name of the hierarchy element
     */
    private String name;

    /**
     * Name of the hierarchy table
     */
    private String tableName;

    /**
     * If the <Hierarchy> element has hasAll="false", the 'all' level is
     * suppressed. The default member of that dimension will now be the first
     * member of the first level; for example, in a Time hierarchy, it will be
     * the first year in the hierarchy. Changing the default member can be
     * confusing, so you should generally use hasAll="true"
     */
    private boolean hasAll;

    /**
     * Table primary key
     */
    private String primaryKey;

    /**
     * Existing level in the dimension
     */
    private List<Level> levels = new ArrayList<>();

    /**
     * Add a new level to the current dimension
     *
     * @param level
     */
    public void addLevel(final Level level) {

        log.debug("Adding the level {}", level == null ? "" : level.getName());
        levels.add(level);
    }

    /**
     * Remove the specified level from the current dimension
     *
     * @param level
     */
    public void removeLevel(final Level level) {

        log.debug("Removing the level {}", level == null ? "" : level.getName());
        levels.remove(level);
    }

    /**
     * Add some levels to the current dimension
     *
     * @param someLevels
     */
    public void addLevels(Collection<Level> someLevels) {

        log.debug("Adding some levels to the current dimension");
        levels.addAll(someLevels);
    }

    /**
     * Return {@code true} if the current dimension contains the specified level
     *
     * @param level
     * @return
     */
    public boolean hasLevel(final Level level) {

        return levels.contains(level);
    }

    /**
     * Clear all the levels of the current dimension
     */
    public void clearLevels() {

        log.debug("Clearing levels of the hierarchy {}", name);
        levels.clear();
    }
}
