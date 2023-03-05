package io.quarkus.primefaces.runtime.graal;

import java.io.IOException;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;

import org.primefaces.component.feedreader.FeedReaderRenderer;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

/**
 * Rome RSS library uses JDOM XML parsing and its failing so not worth investigating.
 */
@TargetClass(FeedReaderRenderer.class)
public final class SubstituteFeedReaderRenderer {

    @Substitute
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        throw new IOException("FeedReader component is not supported in native mode.");
    }
}
