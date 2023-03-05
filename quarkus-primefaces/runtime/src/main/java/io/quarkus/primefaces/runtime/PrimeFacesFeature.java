package io.quarkus.primefaces.runtime;

import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport;

public class PrimeFacesFeature implements Feature {

    private final static String REASON = "PrimeFaces runtime initialization";

    @Override
    public void afterRegistration(AfterRegistrationAccess access) {
        final RuntimeClassInitializationSupport runtimeInit = ImageSingletons.lookup(
                RuntimeClassInitializationSupport.class);

        // Barcode component is optional but must register this for native mode since it uses AWT
        runtimeInit.initializeAtRunTime("org.krysalis.barcode4j.output.bitmap.BitmapEncoderRegistry", REASON);
    }

    @Override
    public String getDescription() {
        return REASON;
    }
}
