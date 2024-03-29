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
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;

public class DelegatingLogFactory extends LogFactory {
    @Override
    public Log getInstance(Class clazz) throws LogConfigurationException {
        return MetaFactory.getInstance().getLogFactory().getInstance(clazz);
    }

    @Override
    public Log getInstance(String name) throws LogConfigurationException {
        return MetaFactory.getInstance().getLogFactory().getInstance(name);
    }
}
