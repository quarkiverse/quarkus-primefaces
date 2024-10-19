package io.quarkiverse.primefaces.deployment;

import java.util.ArrayList;
import java.util.List;

import org.jboss.jandex.DotName;
import org.primefaces.model.file.CommonsUploadedFile;
import org.primefaces.util.Constants;
import org.primefaces.util.PropertyDescriptorResolver;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanDefiningAnnotationBuildItem;
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
import io.quarkus.primefaces.runtime.PrimeFacesFeature;
import io.quarkus.primefaces.runtime.PrimeFacesRecorder;
import io.quarkus.undertow.deployment.ServletInitParamBuildItem;

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
        index.produce(new IndexDependencyBuildItem("io.nayuki", "qrcodegen"));
        index.produce(new IndexDependencyBuildItem("org.overviewproject", "mime-types"));
        index.produce(new IndexDependencyBuildItem("org.primefaces", "primefaces"));

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
                "META-INF/primefaces.taglib.xml",
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

        // Chart XDev models
        classNames.addAll(collectClassesInPackage(combinedIndex, "software.xdev.chartjs.model"));

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

    // TODO: Remove after MyFaces 4.0.3
    @BuildStep
    void temporaryMyFacesStuff(BuildProducer<AdditionalBeanBuildItem> additionalBean,
            BuildProducer<BeanDefiningAnnotationBuildItem> beanDefiningAnnotation,
            BuildProducer<ReflectiveClassBuildItem> reflectiveClass) {
        additionalBean.produce(AdditionalBeanBuildItem
                .unremovableOf(org.apache.myfaces.push.cdi.WebsocketScopeManager.ApplicationScope.class));
        additionalBean.produce(
                AdditionalBeanBuildItem.unremovableOf(org.apache.myfaces.push.cdi.WebsocketScopeManager.SessionScope.class));
        additionalBean.produce(
                AdditionalBeanBuildItem.unremovableOf(org.apache.myfaces.push.cdi.WebsocketScopeManager.ViewScope.class));

        beanDefiningAnnotation
                .produce(new BeanDefiningAnnotationBuildItem(DotName.createSimple(jakarta.faces.annotation.View.class)));

        // TODO: remove in MyFaces 4.0.3
        reflectiveClass.produce(ReflectiveClassBuildItem
                .builder("org.apache.myfaces.view.facelets.component.RepeatStatus", "org.apache.myfaces.push.EndpointImpl")
                .methods(true).fields(true).build());

    }

    @BuildStep
    void enforceInitParams(BuildProducer<ServletInitParamBuildItem> initParam) {
        // only native uploading is supported no need for Commons FileUpload
        initParam.produce(new ServletInitParamBuildItem(Constants.ContextParams.UPLOADER, "native"));
    }
}