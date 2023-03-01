package io.quarkus.primefaces.extensions.runtime;

import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport;

public class PrimeFacesExtensionsFeature implements Feature {

    private final static String REASON = "PrimeFaces Extensions runtime initialization";

    @Override
    public String getDescription() {
        return REASON;
    }

    @Override
    public void afterRegistration(AfterRegistrationAccess access) {
        final RuntimeClassInitializationSupport runtimeInit = ImageSingletons.lookup(
                RuntimeClassInitializationSupport.class);

        // Commons Lang3 is optional but a lot of PFE users use it
        runtimeInit.initializeAtRunTime("org.apache.commons.lang3.RandomUtils", REASON);
    }
}
