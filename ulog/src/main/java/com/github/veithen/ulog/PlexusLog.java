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

import org.apache.commons.logging.Log;
import org.codehaus.plexus.logging.Logger;

final class PlexusLog implements Log {
    private final Logger logger;

    public PlexusLog(Logger logger) {
        this.logger = logger;
    }
    
    public void trace(Object message) {
        logger.debug(message.toString());
    }
    
    public void trace(Object message, Throwable t) {
        logger.debug(message.toString(), t);
    }
    
    public void debug(Object message) {
        logger.debug(message.toString());
    }
    
    public void debug(Object message, Throwable t) {
        logger.debug(message.toString(), t);
    }
    
    public void info(Object message) {
        logger.info(message.toString());
    }
    
    public void info(Object message, Throwable t) {
        logger.info(message.toString(), t);
    }
    
    public void warn(Object message) {
        logger.warn(message.toString());
    }
    
    public void warn(Object message, Throwable t) {
        logger.warn(message.toString(), t);
    }
    
    public void error(Object message) {
        logger.error(message.toString());
    }
    
    public void error(Object message, Throwable t) {
        logger.error(message.toString(), t);
    }
    
    public void fatal(Object message) {
        logger.fatalError(message.toString());
    }
    
    public void fatal(Object message, Throwable t) {
        logger.fatalError(message.toString(), t);
    }

    public boolean isTraceEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    public boolean isFatalEnabled() {
        return logger.isFatalErrorEnabled();
    }
}
