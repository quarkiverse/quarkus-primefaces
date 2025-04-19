package io.quarkiverse.primefaces.extensions.deployment;

import java.util.ArrayList;
import java.util.List;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;

import io.quarkus.arc.deployment.KnownCompatibleBeanArchiveBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.AdditionalApplicationArchiveMarkerBuildItem;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.IndexDependencyBuildItem;
import io.quarkus.deployment.builditem.NativeImageFeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBundleBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourcePatternsBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;
import io.quarkus.primefaces.extensions.runtime.PrimeFacesExtensionsFeature;
import io.quarkus.primefaces.extensions.runtime.PrimeFacesExtensionsRecorder;

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
        index.produce(new IndexDependencyBuildItem("org.primefaces.extensions", "primefaces-extensions"));
        index.produce(new IndexDependencyBuildItem("org.primefaces.extensions", "resources-monacoeditor"));
        index.produce(new IndexDependencyBuildItem("com.google.code.gson", "gson"));
        index.produce(new IndexDependencyBuildItem("com.googlecode.libphonenumber", "libphonenumber"));
        index.produce(new IndexDependencyBuildItem("com.googlecode.owasp-java-html-sanitizer",
                "owasp-java-html-sanitizer"));
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
    void produceKnownCompatible(BuildProducer<KnownCompatibleBeanArchiveBuildItem> knownCompatibleProducer) {
        // bean discovery mode in beans.xml
        knownCompatibleProducer
                .produce(new KnownCompatibleBeanArchiveBuildItem("org.primefaces.extensions", "primefaces-extensions"));
    }

    @BuildStep
    void substrateResourceBuildItems(BuildProducer<NativeImageResourceBuildItem> nativeImageResourceProducer,
            BuildProducer<NativeImageResourceBundleBuildItem> resourceBundleBuildItem,
            BuildProducer<NativeImageResourcePatternsBuildItem> nativeImageResourcePatterns) {
        // resources
        nativeImageResourceProducer.produce(new NativeImageResourceBuildItem(
                "META-INF/maven/org.primefaces.extensions/primefaces-extensions/pom.properties",
                "META-INF/primefaces-extensions.taglib.xml",
                "META-INF/primefaces-extensions.urn.taglib.xml",
                "META-INF/faces-config.xml",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE.txt"));

        // lib phone number and localized
        final NativeImageResourcePatternsBuildItem.Builder builder = NativeImageResourcePatternsBuildItem.builder();
        builder.includeGlob("**/phonenumbers/data/**");
        builder.includeGlob("pfe-localized/**");
        nativeImageResourcePatterns.produce(builder.build());

        // message bundles
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.extensions.Messages"));
    }

    @BuildStep
    @Record(ExecutionTime.STATIC_INIT)
    void registerForReflection(PrimeFacesExtensionsRecorder recorder,
            BuildProducer<ReflectiveClassBuildItem> reflectiveClass,
            CombinedIndexBuildItem combinedIndex) {
        // All utilities
        final List<String> classNames = new ArrayList<>(
                collectClassesInPackage(combinedIndex, "org.primefaces.extensions.util"));

        // Monaco Editor
        classNames.add(java.util.Locale.class.getName());

        // methods and fields
        reflectiveClass.produce(
                ReflectiveClassBuildItem.builder(classNames.toArray(new String[0])).methods(true)
                        .fields(true).build());

        // neither
        reflectiveClass.produce(ReflectiveClassBuildItem.builder(
                org.primefaces.extensions.config.PrimeExtensionsEnvironment.class.getName()).build());
    }

    @BuildStep
    @Record(ExecutionTime.STATIC_INIT)
    void registerForSerialization(PrimeFacesExtensionsRecorder recorder,
            BuildProducer<ReflectiveClassBuildItem> reflectiveClass,
            CombinedIndexBuildItem combinedIndex) {

        // All models
        final List<String> classNames = new ArrayList<>(
                collectClassesInPackage(combinedIndex, "org.primefaces.extensions.model"));

        // serialization reflection
        reflectiveClass.produce(
                ReflectiveClassBuildItem.builder(classNames.toArray(new String[0])).methods().fields().serialization().build());
    }

    public List<String> collectClassesInPackage(CombinedIndexBuildItem combinedIndex, String packageName) {
        final List<String> classes = new ArrayList<>();
        final List<DotName> packages = new ArrayList<>(combinedIndex.getIndex().getSubpackages(packageName));
        packages.add(DotName.createSimple(packageName));
        for (DotName aPackage : packages) {
            final List<String> packageClasses = combinedIndex.getIndex()
                    .getClassesInPackage(aPackage)
                    .stream()
                    .map(ClassInfo::toString)
                    .toList();
            classes.addAll(packageClasses);
        }
        return classes;
    }
}
