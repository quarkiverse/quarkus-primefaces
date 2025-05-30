package io.quarkiverse.primefaces.deployment;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.util.Constants;
import org.primefaces.util.PropertyDescriptorResolver;

import io.quarkus.arc.deployment.KnownCompatibleBeanArchiveBuildItem;
import io.quarkus.deployment.IsNormal;
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
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;
import io.quarkus.deployment.pkg.builditem.UberJarMergedResourceBuildItem;
import io.quarkus.primefaces.runtime.PrimeFacesFeature;
import io.quarkus.primefaces.runtime.PrimeFacesRecorder;

class PrimeFacesProcessor extends AbstractJandexProcessor {

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
        index.produce(new IndexDependencyBuildItem("org.primefaces", "primefaces"));
        index.produce(new IndexDependencyBuildItem("software.xdev", "chartjs-java-model"));
        index.produce(new IndexDependencyBuildItem("com.apptasticsoftware", "rssreader"));

    }

    @BuildStep
    void produceApplicationArchiveMarker(
            BuildProducer<AdditionalApplicationArchiveMarkerBuildItem> additionalArchiveMarkers) {
        additionalArchiveMarkers.produce(new AdditionalApplicationArchiveMarkerBuildItem("org/primefaces/component"));
    }

    @BuildStep
    void produceKnownCompatible(BuildProducer<KnownCompatibleBeanArchiveBuildItem> knownCompatibleProducer) {
        // bean discovery mode in beans.xml
        knownCompatibleProducer.produce(new KnownCompatibleBeanArchiveBuildItem("org.primefaces", "primefaces"));
    }

    /**
     * Produces `UberJarMergedResourceBuildItem`s for each specified service file to be included in the Uber JAR.
     * <p>
     * This build step is only executed in "normal" mode and registers each of the listed services in
     * the `META-INF/services` directory.
     *
     * @param producer The build item producer for creating `UberJarMergedResourceBuildItem` instances.
     */
    @BuildStep(onlyIf = IsNormal.class)
    void uberJarServiceLoaders(BuildProducer<UberJarMergedResourceBuildItem> producer) {
        List<String> serviceFiles = List.of(
                "services/org.primefaces.component.fileupload.FileUploadDecoder",
                "services/org.primefaces.util.PropertyDescriptorResolver",
                "services/org.primefaces.virusscan.VirusScanner",
                "maven/org.json/json/pom.properties",
                "maven/org.json/json/pom.xml",
                "maven/org.jctools/jctools-core/pom.properties",
                "maven/org.jctools/jctools-core/pom.xml");

        for (String serviceFile : serviceFiles) {
            producer.produce(new UberJarMergedResourceBuildItem("META-INF/" + serviceFile));
        }
    }

    @BuildStep
    void substrateResourceBuildItems(BuildProducer<NativeImageResourceBuildItem> nativeImageResourceProducer,
            BuildProducer<NativeImageResourceBundleBuildItem> resourceBundleBuildItem) {
        nativeImageResourceProducer.produce(new NativeImageResourceBuildItem(
                "META-INF/maven/org.primefaces/primefaces/pom.properties",
                "META-INF/primefaces.taglib.xml",
                "META-INF/primefaces.urn.taglib.xml",
                "META-INF/faces-config.xml",
                "META-INF/web-fragment.xml",
                "META-INF/web.xml",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE.txt",
                "META-INF/services/org.primefaces.component.fileupload.FileUploadDecoder",
                "META-INF/services/org.primefaces.util.PropertyDescriptorResolver",
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
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_hr"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_in"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_it"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_ja"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_ka"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_ko"));
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("org.primefaces.Messages_lt"));
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
    @Record(ExecutionTime.STATIC_INIT)
    void registerForReflection(PrimeFacesRecorder recorder, BuildProducer<ReflectiveClassBuildItem> reflectiveClass,
            CombinedIndexBuildItem combinedIndex) {
        // All utilities
        final List<String> classNames = new ArrayList<>(List.of(
                org.primefaces.component.api.IterationStatus.class.getName(),
                org.primefaces.expression.SearchExpressionUtils.class.getName(),
                org.primefaces.clientwindow.PrimeClientWindowUtils.class.getName(),
                org.primefaces.renderkit.RendererUtils.class.getName(),
                org.primefaces.seo.JsonLD.class.getName(),
                Constants.class.getName(),
                org.primefaces.util.AgentUtils.class.getName(),
                org.primefaces.util.BeanUtils.class.getName(),
                org.primefaces.util.CalendarUtils.class.getName(),
                org.primefaces.util.ComponentTraversalUtils.class.getName(),
                org.primefaces.util.ComponentUtils.class.getName(),
                org.primefaces.util.CompositeUtils.class.getName(),
                org.primefaces.util.DynamicContentSrcBuilder.class.getName(),
                org.primefaces.util.ELUtils.class.getName(),
                org.primefaces.util.EscapeUtils.class.getName(),
                org.primefaces.util.FacetUtils.class.getName(),
                org.primefaces.util.FileUploadUtils.class.getName(),
                org.primefaces.util.GridLayoutUtils.class.getName(),
                org.primefaces.util.HtmlSanitizer.class.getName(),
                org.primefaces.util.IOUtils.class.getName(),
                org.primefaces.util.LangUtils.class.getName(),
                org.primefaces.util.LocaleUtils.class.getName(),
                org.primefaces.util.MessageFactory.class.getName(),
                org.primefaces.util.ResourceUtils.class.getName(),
                org.primefaces.util.SecurityUtils.class.getName(),
                PropertyDescriptorResolver.DefaultResolver.class.getName()));

        // components that need special treatment
        classNames.add(org.primefaces.component.fileupload.NativeFileUploadDecoder.class.getName());
        classNames.add(org.primefaces.application.exceptionhandler.ExceptionInfo.class.getName());
        classNames.add(org.primefaces.component.organigram.OrganigramHelper.class.getName());
        classNames.addAll(collectImplementors(combinedIndex, PropertyDescriptorResolver.class.getName()));

        // Exporters
        classNames.addAll(collectImplementors(combinedIndex, org.primefaces.component.export.Exporter.class.getName()));
        classNames.addAll(collectImplementors(combinedIndex, org.primefaces.component.export.ExporterOptions.class.getName()));

        // method reflection
        reflectiveClass.produce(
                ReflectiveClassBuildItem.builder(classNames.toArray(new String[0])).methods().fields().build());

        // neither
        reflectiveClass.produce(
                ReflectiveClassBuildItem.builder(org.primefaces.config.PrimeEnvironment.class.getName()).build());
    }

    @BuildStep
    @Record(ExecutionTime.STATIC_INIT)
    void registerForSerialization(PrimeFacesRecorder recorder, BuildProducer<ReflectiveClassBuildItem> reflectiveClass,
            CombinedIndexBuildItem combinedIndex) {
        // All models
        final List<String> models = collectClassesInPackage(combinedIndex, "org.primefaces.model");
        final List<String> classNames = new ArrayList<>(models);

        // Chart XDev models
        classNames.addAll(collectClassesInPackage(combinedIndex, "software.xdev.chartjs.model"));

        // PrimeFaces
        classNames.add(org.primefaces.component.api.SavedState.class.getName());
        classNames.add(org.primefaces.component.api.UITableState.class.getName());

        // serialization reflection
        reflectiveClass.produce(
                ReflectiveClassBuildItem.builder(classNames.toArray(new String[0])).methods().fields().serialization().build());
    }
}
