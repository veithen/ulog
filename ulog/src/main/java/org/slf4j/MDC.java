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
package org.slf4j;

import java.util.Map;

import org.slf4j.spi.MDCAdapter;

public class MDC {
    private MDC() {}
    
    public static void put(String key, String val) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        getMDCAdapter().put(key, val);        
    }
    
    public static String get(String key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        return getMDCAdapter().get(key);
    }
    
    public static void remove(String key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        getMDCAdapter().remove(key);
    }
    
    public static void clear() {
        getMDCAdapter().clear();
    }

    public static Map getCopyOfContextMap() {
        return getMDCAdapter().getCopyOfContextMap();
    }
    
    public static void setContextMap(Map contextMap) {
        getMDCAdapter().setContextMap(contextMap);
    }
    
    public static MDCAdapter getMDCAdapter() {
        return null; // TODO
    }
}
