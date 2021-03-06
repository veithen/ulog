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
package com.github.veithen.ulog.itest.slf4j.nop;

import junit.framework.TestCase;

import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;

public class Test extends TestCase {
    public void testCommonsLogging() {
        assertEquals(
                "org.apache.commons.logging.impl.NoOpLog",
                LogFactory.getLog("test").getClass().getName());
    }

    public void testSLF4J() {
        assertEquals(
                "org.slf4j.helpers.NOPLogger",
                LoggerFactory.getLogger("test").getClass().getName());
    }
}
