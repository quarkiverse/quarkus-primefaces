package io.quarkiverse.primefaces.deployment;

import java.util.List;

import io.quarkus.deployment.IsNormal;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.IndexDependencyBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBundleBuildItem;
import io.quarkus.deployment.pkg.builditem.UberJarMergedResourceBuildItem;

/**
 * The {@code MimeTypesProcessor} class provides build steps to handle transitive dependencies and resources
 * required for MIME type handling in a Quarkus environment.
 * <p>
 * This processor extends {@link AbstractJandexProcessor} and uses Quarkus build steps to:
 * <ul>
 * <li>Index transitive dependencies.</li>
 * <li>Produce resources for Uber JARs.</li>
 * <li>Register MIME type cache resources for native image builds.</li>
 * </ul>
 */
class MimeTypesProcessor extends AbstractJandexProcessor {

    /**
     * Registers transitive dependencies to be indexed for Jandex processing.
     * <p>
     * This step produces an {@link IndexDependencyBuildItem} for the "mime-types" library, ensuring
     * that relevant classes are indexed by Jandex for use in the Quarkus build process.
     *
     * @param index the build producer for {@link IndexDependencyBuildItem} instances
     */
    @BuildStep
    void indexTransitiveDependencies(BuildProducer<IndexDependencyBuildItem> index) {
        index.produce(new IndexDependencyBuildItem("org.overviewproject", "mime-types"));
    }

    /**
     * Produces {@link UberJarMergedResourceBuildItem}s for each specified service file to be included in the Uber JAR.
     * <p>
     * This build step is only executed in "normal" mode and registers each of the listed services
     * in the {@code META-INF/services} directory, which allows the Uber JAR to load specific service implementations.
     *
     * @param producer the build producer for creating {@link UberJarMergedResourceBuildItem} instances
     */
    @BuildStep(onlyIf = IsNormal.class)
    void uberJarServiceLoaders(BuildProducer<UberJarMergedResourceBuildItem> producer) {
        List<String> serviceFiles = List.of("java.nio.file.spi.FileTypeDetector");

        for (String serviceFile : serviceFiles) {
            producer.produce(new UberJarMergedResourceBuildItem("META-INF/services/" + serviceFile));
        }
    }

    /**
     * Produces a {@link NativeImageResourceBundleBuildItem} to include the MIME type cache resource
     * in native images.
     * <p>
     * This ensures that MIME type information is available in native images, which is required for
     * applications using the "mime-types" library for file type detection.
     *
     * @param resourceBundleBuildItem the build producer for {@link NativeImageResourceBundleBuildItem} instances
     */
    @BuildStep
    void mimeTypeResourceBuildItems(BuildProducer<NativeImageResourceBundleBuildItem> resourceBundleBuildItem) {
        resourceBundleBuildItem.produce(new NativeImageResourceBundleBuildItem("mime.cache"));
    }
}