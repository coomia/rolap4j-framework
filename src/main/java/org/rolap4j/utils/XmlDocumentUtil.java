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

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.rolap4j.exceptions.FileNotFoundException;
import org.rolap4j.exceptions.ParsingException;

/**
 * Created by andriantomanga on 14/05/16.
 *
 * @version 1.0-RELEASE
 * @since 1.0-RELEASE
 */
@Slf4j
public final class XmlDocumentUtil {

    private XmlDocumentUtil() {
        throw new AssertionError();
    }

    /**
     * Create an instance of {@link Document} from a specified xml file.
     *
     * @param xmlFilePath
     * @return an instance of {@link Document}
     * @throws FileNotFoundException The specified file is not found
     * @throws ParsingException      An error occurred when parsing the xml file
     */
    public static Document createDocumentFromXml(final String xmlFilePath) throws FileNotFoundException, ParsingException {

        if (null == xmlFilePath) {
            log.error("XmlDocumentUtil.createDocumentFromXml(...) method has a required argument which is the path to the file to be parsed");
            throw new IllegalStateException("The file path must be specified");
        }

        if (!FileUtil.fileExists(xmlFilePath)) {
            log.error("The following file was not found : {}", xmlFilePath);
            throw new FileNotFoundException(xmlFilePath);
        }

        Document document = null;
        try {
            log.debug("Building document from the following file {}", xmlFilePath);
            document = new SAXReader().read(xmlFilePath);
        } catch (DocumentException e) {
            throw new ParsingException(e.getMessage());
        }
        return document;
    }


}
