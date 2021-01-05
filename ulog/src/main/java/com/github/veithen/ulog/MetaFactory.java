/*-
 * #%L
 * ULog
 * %%
 * Copyright (C) 2011 - 2018 Andreas Veithen
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.github.veithen.ulog;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.SLF4JLogFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.impl.JDK14LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public final class MetaFactory {
    // We use java.util.logging as a fallback during discovery
    private static final Logger logger = Logger.getLogger(MetaFactory.class.getName());

    private static Boolean log4jAvailable;
    private static MetaFactory instance;

    private final LogFactory logFactory;
    private final ILoggerFactory loggerFactory;

    MetaFactory(LogFactory logFactory, ILoggerFactory loggerFactory) {
        this.logFactory = logFactory;
        this.loggerFactory = loggerFactory;
    }

    public static synchronized MetaFactory getInstance() {
        if (instance == null) {
            LoggerFactoryBinder binder = getLoggerFactoryBinder();
            if (binder != null) {
                String loggerFactoryClass = binder.getLoggerFactoryClassStr();
                if (loggerFactoryClass.equals("org.slf4j.helpers.NOPLoggerFactory")) {
                    instance = new MetaFactory(new NoOpLogFactory(), new NOPLoggerFactory());
                } else if (loggerFactoryClass.equals("org.slf4j.impl.Log4jLoggerFactory")) {
                    if (isLog4jAvailable()) {
                        instance = new MetaFactory(new Log4jLogFactory(), new Log4jLoggerFactory());
                    } else {
                        logger.log(
                                Level.WARNING,
                                "slf4j-log4j12 detected, but log4j is not available");
                    }
                } else {
                    instance = new MetaFactory(new SLF4JLogFactory(), binder.getLoggerFactory());
                }
            }
            if (instance == null) {
                if (isLog4jAvailable()) {
                    instance = new MetaFactory(new Log4jLogFactory(), new Log4jLoggerFactory());
                } else {
                    instance = new MetaFactory(new Jdk14LogFactory(), new JDK14LoggerFactory());
                }
            }
        }
        return instance;
    }

    static synchronized void setInstance(MetaFactory metaFactory) {
        if (instance != null) {
            throw new IllegalStateException("MetaFactory instance already set");
        } else {
            instance = metaFactory;
        }
    }

    private static boolean isLog4jAvailable() {
        if (log4jAvailable == null) {
            try {
                MetaFactory.class.getClassLoader().loadClass("org.apache.log4j.Logger");
                log4jAvailable = Boolean.TRUE;
            } catch (ClassNotFoundException ex) {
                log4jAvailable = Boolean.FALSE;
            }
        }
        return log4jAvailable.booleanValue();
    }

    private static LoggerFactoryBinder getLoggerFactoryBinder() {
        Class binderClass;
        try {
            binderClass =
                    MetaFactory.class
                            .getClassLoader()
                            .loadClass("org.slf4j.impl.StaticLoggerBinder");
        } catch (ClassNotFoundException ex) {
            return null;
        }
        try {
            return (LoggerFactoryBinder)
                    binderClass.getMethod("getSingleton", new Class[0]).invoke(null, new Object[0]);
        } catch (InvocationTargetException ex) {
            logger.log(Level.WARNING, "Unable to get the SLF4J LoggerFactoryBinder", ex.getCause());
            return null;
        } catch (Throwable ex) {
            logger.log(Level.WARNING, "Unable to get the SLF4J LoggerFactoryBinder", ex);
            return null;
        }
    }

    public LogFactory getLogFactory() {
        return logFactory;
    }

    public ILoggerFactory getILoggerFactory() {
        return loggerFactory;
    }
}
