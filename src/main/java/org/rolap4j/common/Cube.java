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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andriantomanga on 12/05/16.
 *
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Data
@EqualsAndHashCode(exclude = {"dimensions"})
@ToString(exclude = {"dimensions"}, callSuper = true)
public class Cube extends Element {

    protected String tableName;

    protected String defaultMeasure;

    protected Set<Dimension> dimensions = new HashSet<>();

    protected Set<Measure> measures = new HashSet<>();


    public void addDimension(final Dimension dimension) {

        dimensions.add(dimension);
    }

    public void removeDimension(final Dimension dimension) {

        dimensions.remove(dimension);
    }

    public void addDimensions(Collection<Dimension> someDimensions) {

        dimensions.addAll(someDimensions);
    }

    public boolean hasDimension(final Dimension dimension) {

        return dimensions.contains(dimension);
    }

    public void clearDimension() {

        dimensions.clear();
    }

    public void addMeasure(final Measure measure) {

        measures.add(measure);
    }

    public void removeMeasure(final Measure measure) {

        measures.remove(measure);
    }

    public void addMeasures(Collection<Measure> someMeasures) {

        measures.addAll(someMeasures);
    }

    public boolean hasMeasure(final Measure measure) {

        return measures.contains(measure);
    }

    public void clearMeasures() {

        measures.clear();
    }


}
