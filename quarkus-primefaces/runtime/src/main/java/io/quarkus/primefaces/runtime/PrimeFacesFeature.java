package io.quarkus.primefaces.runtime;

import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeClassInitialization;

public class PrimeFacesFeature implements Feature {

    @Override
    public void afterRegistration(AfterRegistrationAccess access) {
        // XDEV Charts.js uses SecureRandom
        RuntimeClassInitialization.initializeAtRunTime("software.xdev.chartjs.model.color.Color");
        RuntimeClassInitialization.initializeAtRunTime("software.xdev.chartjs.model.color.HSLAColor");
        RuntimeClassInitialization.initializeAtRunTime("software.xdev.chartjs.model.color.RGBAColor");

        // PrimeFaces
        RuntimeClassInitialization.initializeAtRunTime(org.primefaces.el.InterceptingResolver.class.getName());
        RuntimeClassInitialization.initializeAtRunTime(org.primefaces.util.ChartUtils.class.getName());
    }

    @Override
    public String getDescription() {
        return "PrimeFaces runtime initialization";
    }
}