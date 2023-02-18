package io.quarkus.primefaces.extensions.runtime;

import org.graalvm.nativeimage.hosted.Feature;

public class PrimeFacesExtensionsFeature implements Feature {

    private final static String REASON = "Quarkus runtime initialization for PrimeFaces Extensions";

    @Override
    public String getDescription() {
        return REASON;
    }
}
