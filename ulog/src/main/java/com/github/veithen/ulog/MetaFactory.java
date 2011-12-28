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
package com.github.veithen.ulog;

import org.apache.commons.logging.LogFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.JDK14LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;

public final class MetaFactory {
    private static MetaFactory instance;
    
    private final LogFactory logFactory;
    private final ILoggerFactory loggerFactory;
    
    private MetaFactory(LogFactory logFactory, ILoggerFactory loggerFactory) {
        this.logFactory = logFactory;
        this.loggerFactory = loggerFactory;
    }

    public static synchronized MetaFactory getInstance() {
        if (instance == null) {
            ClassLoader cl = MetaFactory.class.getClassLoader();
            try {
                cl.loadClass("org.apache.log4j.Logger");
                instance = new MetaFactory(new Log4jLogFactory(), new Log4jLoggerFactory());
            } catch (ClassNotFoundException ex) {
                // continue
            }
            if (instance == null) {
                instance = new MetaFactory(new Jdk14LogFactory(), new JDK14LoggerFactory());
            }
        }
        return instance;
    }

    public LogFactory getLogFactory() {
        return logFactory;
    }

    public ILoggerFactory getILoggerFactory() {
        return loggerFactory;
    }
}
