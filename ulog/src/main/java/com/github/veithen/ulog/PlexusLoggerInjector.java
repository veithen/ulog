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

import org.codehaus.plexus.logging.LoggerManager;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;

/** @plexus.component role="com.github.veithen.ulog.PlexusLoggerInjector" role-hint="default" */
public final class PlexusLoggerInjector implements Initializable {
    /** @plexus.requirement */
    private LoggerManager loggerManager;

    public void initialize() throws InitializationException {
        // TODO: implement SLF4J stuff
        MetaFactory.setInstance(new MetaFactory(new PlexusLogFactory(loggerManager), null));
    }
}
