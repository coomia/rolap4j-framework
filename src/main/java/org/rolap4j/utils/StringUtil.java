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

/**
 * Created by andriantomanga on 14/05/16.
 *
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
public final class StringUtil {

    private StringUtil() {

        throw new AssertionError();
    }

    /**
     * Check if the <tt>value</tt> is empty
     *
     * @param value Value to be checked
     * @return {@code true} if the value is empty
     * @see #isNotEmpty(String)
     */
    public static boolean isEmpty(final String value) {

        return value == null || value.trim().isEmpty();
    }


    /**
     * Check if the <tt>value</tt> is not empty
     *
     * @param value Value to be checked
     * @return {@code true} if the value is not empty
     * @see #isEmpty(String)
     */
    public static boolean isNotEmpty(final String value) {

        return !isEmpty(value);
    }


}
