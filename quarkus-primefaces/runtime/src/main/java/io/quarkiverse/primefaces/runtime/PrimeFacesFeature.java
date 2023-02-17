package io.quarkus.primefaces.runtime;

import org.graalvm.nativeimage.hosted.Feature;

public class PrimeFacesFeature implements Feature {

    private final static String REASON = "Quarkus runtime init for PrimeFaces";

    @Override
    public String getDescription() {
        return REASON;
    }
}
