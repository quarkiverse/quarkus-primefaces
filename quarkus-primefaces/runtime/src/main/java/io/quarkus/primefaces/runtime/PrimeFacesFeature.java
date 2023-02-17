package io.quarkus.primefaces.runtime;

import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport;

public class PrimeFacesFeature implements Feature {

    private final static String REASON = "Quarkus runtime init for PrimeFaces";

    @Override
    public void afterRegistration(AfterRegistrationAccess access) {
        final RuntimeClassInitializationSupport runtimeInit = ImageSingletons.lookup(RuntimeClassInitializationSupport.class);

        runtimeInit.initializeAtRunTime(org.primefaces.util.ExcelStylesManager.class.getName(), REASON);
    }

    @Override
    public String getDescription() {
        return REASON;
    }
}
