package io.quarkus.primefaces.extensions.runtime;

import org.graalvm.nativeimage.hosted.Feature;

public class PrimeFacesExtensionsFeature implements Feature {

    private final static String REASON = "PrimeFaces Extensions runtime initialization";

    @Override
    public String getDescription() {
        return REASON;
    }
}
