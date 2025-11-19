package io.quarkus.primefaces.runtime.graalvm;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

import jakarta.el.ELContext;

import com.oracle.svm.core.annotate.Delete;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(className = "org.primefaces.el.InterceptingResolver")
final class Substitute_InterceptingResolver {

    @Delete
    public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
        return null;
    }
}