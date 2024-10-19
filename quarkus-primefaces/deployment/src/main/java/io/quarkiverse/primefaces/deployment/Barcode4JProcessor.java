package io.quarkiverse.primefaces.deployment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.IndexDependencyBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBundleBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;
import io.quarkus.deployment.builditem.nativeimage.RuntimeInitializedPackageBuildItem;
import io.quarkus.logging.Log;

/**
 * Register Barcode4J library.
 */
public class Barcode4JProcessor extends AbstractJandexProcessor {

    @BuildStep
    void indexTransitiveDependencies(BuildProducer<IndexDependencyBuildItem> index) {
        index.produce(new IndexDependencyBuildItem("org.primefaces.extensions", "barcode4j-light"));
    }

    @BuildStep
    void runtimeBarcodeInitializedClasses(BuildProducer<RuntimeInitializedPackageBuildItem> runtimeInitializedPackages) {
        //@formatter:off
        List<String> classes = new ArrayList<>(
                Stream.of(org.krysalis.barcode4j.output.bitmap.BitmapEncoderRegistry.class.getName(),
                        "javax.swing.plaf.metal",
                        "javax.swing.text.html",
                        "javax.swing.text.rtf",
                        "sun.datatransfer",
                        "sun.swing"
                ).toList());
        //@formatter:on
        Log.debugf("Barcode4J Runtime: %s", classes);
        classes.stream()
                .map(RuntimeInitializedPackageBuildItem::new)
                .forEach(runtimeInitializedPackages::produce);
    }

    @BuildStep
    void registerBarcodeForReflection(BuildProducer<ReflectiveClassBuildItem> reflectiveClass,
            CombinedIndexBuildItem combinedIndex) {
        final List<String> classNames = new ArrayList<>();
        classNames.add("javax.imageio.ImageIO");
        classNames.add(org.krysalis.barcode4j.output.bitmap.ImageIOBitmapEncoder.class.getName());

        Log.debugf("Barcode4J Reflection: %s", classNames);
        // methods and fields
        reflectiveClass.produce(
                ReflectiveClassBuildItem.builder(classNames.toArray(new String[0])).methods().fields().serialization().build());
    }

    @BuildStep
    void registerBarcodeResources(BuildProducer<NativeImageResourceBuildItem> nativeImageResourceProducer,
            BuildProducer<NativeImageResourceBundleBuildItem> resourceBundleBuildItem) {
        // Register individual resource files
        nativeImageResourceProducer.produce(new NativeImageResourceBuildItem(
                "org/krysalis/barcode4j/impl/fourstate/usps-4bc-bar-to-character-table.csv"));

        // Register resource bundles
        resourceBundleBuildItem
                .produce(new NativeImageResourceBundleBuildItem("org.krysalis.barcode4j.impl.code128.EAN128AIs"));
    }
}