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
package org.apache.commons.logging;

import com.github.veithen.ulog.Jdk14LogFactory;
import com.github.veithen.ulog.Log4jLogFactory;

public abstract class LogFactory {
    private static LogFactory instance;
    
    public synchronized static LogFactory getFactory() throws LogConfigurationException {
        if (instance == null) {
            ClassLoader cl = LogFactory.class.getClassLoader();
            try {
                cl.loadClass("org.apache.log4j.Logger");
                instance = new Log4jLogFactory();
            } catch (ClassNotFoundException ex) {
                // continue
            }
            if (instance == null) {
                instance = new Jdk14LogFactory();
            }
        }
        return instance;
    }
    
    public static Log getLog(Class clazz) throws LogConfigurationException {
        return getFactory().getInstance(clazz);
    }

    public static Log getLog(String name) throws LogConfigurationException {
        return getFactory().getInstance(name);
    }

    public abstract Log getInstance(Class clazz) throws LogConfigurationException;

    public abstract Log getInstance(String name) throws LogConfigurationException;
}
