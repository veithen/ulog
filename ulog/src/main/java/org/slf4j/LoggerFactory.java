/*
 * Copyright 2011 Andreas Veithen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.slf4j;

import org.apache.commons.logging.LogFactory;
import org.slf4j.impl.JDK14LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;

public final class LoggerFactory {
    private static ILoggerFactory instance;
    
    private LoggerFactory() {}
    
    public synchronized static ILoggerFactory getILoggerFactory() {
        if (instance == null) {
            ClassLoader cl = LogFactory.class.getClassLoader();
            try {
                cl.loadClass("org.apache.log4j.Logger");
                instance = new Log4jLoggerFactory();
            } catch (ClassNotFoundException ex) {
                // continue
            }
            if (instance == null) {
                instance = new JDK14LoggerFactory();
            }
        }
        return instance;
    }

    public static Logger getLogger(Class clazz) {
        return getLogger(clazz.getName());
    }

    public static Logger getLogger(String name) {
        return getILoggerFactory().getLogger(name);
    }
}
