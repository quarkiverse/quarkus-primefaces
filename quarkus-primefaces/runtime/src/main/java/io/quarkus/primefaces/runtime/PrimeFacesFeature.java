package io.quarkus.primefaces.runtime;

import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeClassInitialization;
import org.krysalis.barcode4j.output.bitmap.BitmapEncoderRegistry;

public class PrimeFacesFeature implements Feature {
    private final static String REASON = "PrimeFaces runtime initialization";

    @Override
    public void afterRegistration(AfterRegistrationAccess access) {
        // Barcode component is optional but must register this for native mode since it uses AWT
        RuntimeClassInitialization.initializeAtRunTime(BitmapEncoderRegistry.class.getName());
    }

    @Override
    public String getDescription() {
        return REASON;
    }
}