package io.quarkus.primefaces.runtime;

import org.apache.myfaces.cdi.view.ViewScopeBeanHolder;
import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport;

public class PrimeFacesFeature implements Feature {

    private final static String REASON = "Quarkus runtime initialization for PrimeFaces";

    @Override
    public void afterRegistration(AfterRegistrationAccess access) {
        final RuntimeClassInitializationSupport runtimeInit = ImageSingletons.lookup(RuntimeClassInitializationSupport.class);

        // TODO: being fixed in MyFaces 2.3-M8
        runtimeInit.initializeAtRunTime(ViewScopeBeanHolder.class, REASON);
    }

    @Override
    public String getDescription() {
        return REASON;
    }
}
