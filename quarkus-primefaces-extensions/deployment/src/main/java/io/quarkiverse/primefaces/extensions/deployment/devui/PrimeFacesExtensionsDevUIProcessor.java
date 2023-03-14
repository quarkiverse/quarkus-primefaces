package io.quarkiverse.primefaces.extensions.deployment.devui;

import org.primefaces.extensions.util.Constants;

import io.quarkus.deployment.IsDevelopment;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.devui.spi.page.CardPageBuildItem;
import io.quarkus.devui.spi.page.Page;
import io.quarkus.devui.spi.page.PageBuilder;

/**
 * Dev UI card for displaying important details such as the library version.
 */
public class PrimeFacesExtensionsDevUIProcessor {

    private static final String EXTENSION_NAME = "PrimeFaces Extensions";

    @BuildStep(onlyIf = IsDevelopment.class)
    void createCard(BuildProducer<CardPageBuildItem> cardPageBuildItemBuildProducer) {
        final CardPageBuildItem card = new CardPageBuildItem(EXTENSION_NAME);

        final PageBuilder versionPage = Page.externalPageBuilder("Version")
                .icon("font-awesome-solid:book")
                .url("https://primefaces-extensions.github.io/")
                .isHtmlContent()
                .staticLabel(Constants.class.getPackage().getImplementationVersion());
        card.addPage(versionPage);

        card.setCustomCard("qwc-primefaces-extensions-card.js");

        cardPageBuildItemBuildProducer.produce(card);
    }

}
