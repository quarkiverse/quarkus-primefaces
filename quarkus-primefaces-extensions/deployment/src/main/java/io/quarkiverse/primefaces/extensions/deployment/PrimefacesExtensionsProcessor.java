package io.quarkiverse.primefaces.extensions.deployment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.jandex.ClassInfo;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.AdditionalApplicationArchiveMarkerBuildItem;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.IndexDependencyBuildItem;
import io.quarkus.deployment.builditem.NativeImageFeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBundleBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;
import io.quarkus.primefaces.extensions.runtime.PrimeFacesExtensionsFeature;

class PrimefacesExtensionsProcessor {

    private static final String FEATURE = "primefaces-extensions";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    NativeImageFeatureBuildItem nativeImageFeature() {
        return new NativeImageFeatureBuildItem(PrimeFacesExtensionsFeature.class);
    }

    @BuildStep
    void indexTransitiveDependencies(BuildProducer<IndexDependencyBuildItem> index) {
        index.produce(new IndexDependencyBuildItem("org.primefaces.extensions", "resources-monacoeditor"));
        index.produce(new IndexDependencyBuildItem("org.primefaces.extensions", "resources-ckeditor"));
        index.produce(new IndexDependencyBuildItem("com.google.code.gson", "gson"));
        index.produce(new IndexDependencyBuildItem("com.googlecode.libphonenumber", "libphonenumber"));
        index.produce(new IndexDependencyBuildItem("com.googlecode.owasp-java-html-sanitizer", "owasp-java-html-sanitizer"));
        index.produce(new IndexDependencyBuildItem("dev.morphia.morphia", "morphia-core"));
        index.produce(new IndexDependencyBuildItem("org.commonmark", "commonmark"));
    }

    @BuildStep
    void produceApplicationArchiveMarker(
            BuildProducer<AdditionalApplicationArchiveMarkerBuildItem> additionalArchiveMarkers) {
        additionalArchiveMarkers
                .produce(new AdditionalApplicationArchiveMarkerBuildItem("org/primefaces/extensions/component"));
    }

    @BuildStep
    void substrateResourceBuildItems(BuildProducer<NativeImageResourceBuildItem> nativeImageResourceProducer,
            BuildProducer<NativeImageResourceBundleBuildItem> resourceBundleBuildItem) {
        nativeImageResourceProducer.produce(new NativeImageResourceBuildItem(
                "META-INF/maven/org.primefaces.extensions/primefaces-extensions/pom.properties",
                "META-INF/primefaces-extensions.taglib.xml",
                "META-INF/faces-config.xml",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE.txt"));

        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.extensions.Messages"));
    }

    @BuildStep
    void registerForReflection(BuildProducer<ReflectiveClassBuildItem> reflectiveClass,
            CombinedIndexBuildItem combinedIndex) {
        final List<String> classNames = new ArrayList<>();
        // All utilities
        classNames.addAll(
                collectClassesInPackage(combinedIndex, org.primefaces.extensions.util.Constants.class.getPackageName()));

        // methods
        reflectiveClass.produce(new ReflectiveClassBuildItem(true, false, classNames.toArray(new String[classNames.size()])));

        // neither
        reflectiveClass.produce(new ReflectiveClassBuildItem(false, false,
                org.primefaces.extensions.config.PrimeExtensionsEnvironment.class.getName()));
    }

    public List<String> collectClassesInPackage(CombinedIndexBuildItem combinedIndex, String packageName) {
        List<String> classes = combinedIndex.getIndex()
                .getClassesInPackage(packageName)
                .stream()
                .map(ClassInfo::toString)
                .collect(Collectors.toList());
        return classes;
    }
}
