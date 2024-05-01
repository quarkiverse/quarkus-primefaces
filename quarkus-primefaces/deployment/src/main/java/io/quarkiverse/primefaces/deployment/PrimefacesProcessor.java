package io.quarkiverse.primefaces.deployment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.primefaces.model.file.CommonsUploadedFile;
import org.primefaces.util.Constants;
import org.primefaces.util.PropertyDescriptorResolver;

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
import io.quarkus.deployment.pkg.steps.NativeOrNativeSourcesBuild;
import io.quarkus.primefaces.runtime.PrimeFacesFeature;
import io.quarkus.primefaces.runtime.PrimeFacesRecorder;
import io.quarkus.undertow.deployment.ServletInitParamBuildItem;

class PrimefacesProcessor {

    private static final String FEATURE = "primefaces";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep(onlyIf = NativeOrNativeSourcesBuild.class)
    NativeImageFeatureBuildItem nativeImageFeature() {
        return new NativeImageFeatureBuildItem(PrimeFacesFeature.class);
    }

    @BuildStep
    void indexTransitiveDependencies(BuildProducer<IndexDependencyBuildItem> index) {
        index.produce(new IndexDependencyBuildItem("com.googlecode.owasp-java-html-sanitizer",
                "owasp-java-html-sanitizer"));
        index.produce(new IndexDependencyBuildItem("io.nayuki", "qrcodegen"));
        index.produce(new IndexDependencyBuildItem("org.primefaces.extensions", "barcode4j-light"));
        index.produce(new IndexDependencyBuildItem("org.overviewproject", "mime-types"));
        index.produce(new IndexDependencyBuildItem("software.xdev", "chartjs-java-model"));
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

        // mime types
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("mime.cache"));
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
                org.primefaces.util.AgentUtils.class.getName(),
                org.primefaces.util.BeanUtils.class.getName(),
                org.primefaces.util.CalendarUtils.class.getName(),
                org.primefaces.util.ChartUtils.class.getName(),
                org.primefaces.util.ComponentTraversalUtils.class.getName(),
                org.primefaces.util.ComponentUtils.class.getName(),
                org.primefaces.util.CompositeUtils.class.getName(),
                Constants.class.getName(),
                org.primefaces.util.DynamicContentSrcBuilder.class.getName(),
                org.primefaces.util.ELUtils.class.getName(),
                org.primefaces.util.EscapeUtils.class.getName(),
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

        // All models
        List<String> models = collectClassesInPackage(combinedIndex, "org.primefaces.model");
        models.remove(CommonsUploadedFile.class.getName());
        classNames.addAll(models);

        // components that need special treatment
        classNames.add(org.primefaces.component.fileupload.NativeFileUploadDecoder.class.getName());
        classNames.add(org.primefaces.application.exceptionhandler.ExceptionInfo.class.getName());
        classNames.add(org.primefaces.component.organigram.OrganigramHelper.class.getName());
        classNames.addAll(collectImplementors(combinedIndex, PropertyDescriptorResolver.class.getName()));

        // Barcode
        classNames.add("javax.imageio.ImageIO");
        classNames.add(org.krysalis.barcode4j.output.bitmap.ImageIOBitmapEncoder.class.getName());

        // Chart XDev models
        classNames.addAll(collectClassesInPackage(combinedIndex, "software.xdev.chartjs.model"));

        // Exporters
        classNames.addAll(collectImplementors(combinedIndex, org.primefaces.component.export.Exporter.class.getName()));
        classNames.addAll(collectImplementors(combinedIndex, org.primefaces.component.export.ExporterOptions.class.getName()));
        classNames.remove(org.primefaces.component.datatable.export.DataTablePDFExporter.class.getName());
        classNames.remove(org.primefaces.component.treetable.export.TreeTablePDFExporter.class.getName());

        // TODO: remove in MyFaces 4.0.3
        classNames.add("org.apache.myfaces.view.facelets.component.RepeatStatus");

        // method reflection
        reflectiveClass.produce(
                ReflectiveClassBuildItem.builder(classNames.toArray(new String[0])).methods(true)
                        .fields(true).build());

        // neither
        reflectiveClass.produce(
                ReflectiveClassBuildItem.builder(org.primefaces.config.PrimeEnvironment.class.getName()).build());
    }

    @BuildStep
    void enforceInitParams(BuildProducer<ServletInitParamBuildItem> initParam) {
        // only native uploading is supported no need for Commons FileUpload
        initParam.produce(new ServletInitParamBuildItem(Constants.ContextParams.UPLOADER, "native"));
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

    private List<String> collectSubclasses(CombinedIndexBuildItem combinedIndex, String className) {
        List<String> classes = combinedIndex.getIndex()
                .getAllKnownSubclasses(DotName.createSimple(className))
                .stream()
                .map(ClassInfo::toString)
                .collect(Collectors.toList());
        classes.add(className);
        return classes;
    }

    public List<String> collectImplementors(CombinedIndexBuildItem combinedIndex, String className) {
        List<String> classes = combinedIndex.getIndex()
                .getAllKnownImplementors(DotName.createSimple(className))
                .stream()
                .map(ClassInfo::toString)
                .collect(Collectors.toList());
        classes.add(className);
        return classes;
    }
}