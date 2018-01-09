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
package com.github.veithen.ulog.itest.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class Test extends TestCase {
    private DummyAppender appender;
    
    @Override
    protected void setUp() throws Exception {
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("test");
        appender = new DummyAppender();
        logger.addAppender(appender);
    }

    public void testCommonsLogging() {
        Log log = LogFactory.getLog("test");
        String msg = "test with commons logging";
        log.info(msg);
        assertEquals(msg, appender.getLastEvent().getMessage());
    }

    public void testSLF4J() {
        Logger logger = LoggerFactory.getLogger("test");
        String msg = "test with SLF4J";
        logger.info(msg);
        assertEquals(msg, appender.getLastEvent().getMessage());
    }
}
