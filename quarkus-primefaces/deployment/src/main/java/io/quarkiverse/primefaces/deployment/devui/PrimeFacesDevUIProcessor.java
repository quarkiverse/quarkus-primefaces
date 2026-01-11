package io.quarkiverse.primefaces.deployment.devui;

import io.quarkus.deployment.IsDevelopment;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.devui.spi.page.CardPageBuildItem;

/**
 * Dev UI card for displaying important details such as the library version.
 */
public class PrimeFacesDevUIProcessor {

    @BuildStep(onlyIf = IsDevelopment.class)
    void createCard(BuildProducer<CardPageBuildItem> cardPageBuildItemBuildProducer) {
        final CardPageBuildItem card = new CardPageBuildItem();
        card.addLibraryVersion("org.primefaces", "primefaces", "PrimeFaces",
                "https://www.primefaces.org/showcase/");
        cardPageBuildItemBuildProducer.produce(card);
    }

}
