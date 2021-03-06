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
package org.rolap4j.exceptions;

/**
 * Created by andriantomanga on 14/05/16.
 *
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
public class FileNotFoundException extends Rolap4jException {

    public FileNotFoundException(final String filePath) {
        super("Cannot find the following file : " + filePath);
    }

    public FileNotFoundException() {
        super("A file is missing and cannot be read by Rolap4j Framework");
    }
}
