/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.quarkus.primefaces.runtime;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.myfaces.util.lang.ClassUtils;

import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class PrimeFacesRecorder {
    public static final Map<Class<? extends Annotation>, Set<Class<?>>> ANNOTATED_CLASSES = new LinkedHashMap<>();

    @SuppressWarnings("unchecked") //cast to (Class<? extends Annotation>)
    public void registerAnnotatedClass(String annotationName, String clazzName) {
        Class<? extends Annotation> annotation = (Class<? extends Annotation>) ClassUtils.simpleClassForName(annotationName);
        Class<?> clazz = ClassUtils.simpleClassForName(clazzName);

        Set<Class<?>> classes = ANNOTATED_CLASSES.computeIfAbsent(annotation, $ -> new HashSet<>());
        classes.add(clazz);
    }

}
