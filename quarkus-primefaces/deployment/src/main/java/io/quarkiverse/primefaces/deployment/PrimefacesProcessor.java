package io.quarkiverse.primefaces.deployment;

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
import io.quarkus.primefaces.runtime.PrimeFacesFeature;

class PrimefacesProcessor {

    private static final String FEATURE = "primefaces";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    NativeImageFeatureBuildItem nativeImageFeature() {
        return new NativeImageFeatureBuildItem(PrimeFacesFeature.class);
    }

    @BuildStep
    void indexTransitiveDependencies(BuildProducer<IndexDependencyBuildItem> index) {
        index.produce(new IndexDependencyBuildItem("com.googlecode.owasp-java-html-sanitizer", "owasp-java-html-sanitizer"));
        index.produce(new IndexDependencyBuildItem("com.github.librepdf", "openpdf"));
        index.produce(new IndexDependencyBuildItem("io.nayuki", "qrcodegen"));
    }

    @BuildStep
    void produceApplicationArchiveMarker(
            BuildProducer<AdditionalApplicationArchiveMarkerBuildItem> additionalArchiveMarkers) {
        additionalArchiveMarkers.produce(new AdditionalApplicationArchiveMarkerBuildItem("org/primefaces/component"));
    }

    @BuildStep
    void substrateResourceBuildItems(BuildProducer<NativeImageResourceBuildItem> nativeImageResourceProducer,
            BuildProducer<NativeImageResourceBundleBuildItem> resourceBundleBuildItem) {
        nativeImageResourceProducer.produce(new NativeImageResourceBuildItem(
                "META-INF/maven/org.primefaces/primefaces/pom.properties",
                "META-INF/primefaces-p.taglib.xml",
                "META-INF/faces-config.xml",
                "META-INF/web-fragment.xml",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE.txt",
                "META-INF/services/org.primefaces.component.fileupload.FileUploadDecoder",
                "META-INF/services/org.primefaces.virusscan.VirusScanner"));

        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_cs"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_de"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_el"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_en"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_es"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_fa"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_fr"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_hi"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_in"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_it"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_ka"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_ko"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_lv"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_nl"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_no"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_pl"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_pt"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_ro"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_ru"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_sk"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_sv"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_tr"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_zh"));
    }

    @BuildStep
    void registerForReflection(BuildProducer<ReflectiveClassBuildItem> reflectiveClass, CombinedIndexBuildItem combinedIndex) {
        final List<String> classNames = new ArrayList<>();
        // All utilities
        classNames.addAll(
                collectClassesInPackage(combinedIndex, org.primefaces.util.Constants.class.getPackageName()));
        classNames.add(org.primefaces.expression.SearchExpressionUtils.class.getName());
        reflectiveClass.produce(new ReflectiveClassBuildItem(true, false,
                    org.primefaces.expression.SearchExpressionUtils.class.getName(),
                    org.primefaces.util.AgentUtils.class.getName(),
                    org.primefaces.util.BeanUtils.class.getName(),
                    org.primefaces.util.CalendarUtils.class.getName(),
                    org.primefaces.util.ChartUtils.class.getName(),
                    org.primefaces.util.ComponentTraversalUtils.class.getName(),
                    org.primefaces.util.ComponentUtils.class.getName(),
                    org.primefaces.util.CompositeUtils.class.getName(),
                    org.primefaces.util.ELUtils.class.getName(),
                    org.primefaces.util.EscapeUtils.class.getName(),
                    org.primefaces.util.FileUploadUtils.class.getName(),
                    org.primefaces.util.GridLayoutUtils.class.getName(),
                    org.primefaces.util.IOUtils.class.getName(),
                    org.primefaces.util.LangUtils.class.getName(),
                    org.primefaces.util.LocaleUtils.class.getName(),
                    org.primefaces.util.ResourceUtils.class.getName(),
                    org.primefaces.util.SecurityUtils.class.getName()));

        // methods
        reflectiveClass.produce(new ReflectiveClassBuildItem(true, false, classNames.toArray(new String[classNames.size()])));

        // neither
        reflectiveClass.produce(new ReflectiveClassBuildItem(false, false,
                org.primefaces.config.PrimeEnvironment.class.getName(),
                com.lowagie.text.pdf.MappedRandomAccessFile.class.getName()));
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
