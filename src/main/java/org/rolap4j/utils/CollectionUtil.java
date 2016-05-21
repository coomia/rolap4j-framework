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

import java.util.Collection;

/**
 * Created by andriantomanga on 15/05/16.
 *
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
public final class CollectionUtil {

    private CollectionUtil() {

        throw new AssertionError();
    }

    /**
     * Check if the collection is empty or {@code null}
     *
     * @param collection
     * @return {@code true} if the collection is empty or {@code null}
     * @see #isNotEmpty(Collection)
     */
    public static boolean isEmpty(Collection<?> collection) {

        return collection == null || collection.isEmpty();
    }

    /**
     * Check if the collection is not empty or not {@code null}
     *
     * @param collection
     * @return {@code true} if the collection is not empty or not {@code null}
     * @see #isEmpty(Collection)
     */
    public static boolean isNotEmpty(Collection<?> collection) {

        return !isEmpty(collection);
    }
}
