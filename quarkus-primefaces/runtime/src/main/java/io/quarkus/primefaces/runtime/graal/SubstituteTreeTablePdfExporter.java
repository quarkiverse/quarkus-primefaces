package io.quarkus.primefaces.runtime.graal;

import org.primefaces.component.treetable.export.TreeTablePDFExporter;

import com.lowagie.text.Paragraph;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

/**
 * iText Paragraph.add(object) is ambiguous and is confusing the native parser with the error:
 * "Discovered unresolved method during parsing: com.lowagie.text.Paragraph.add(com.lowagie.text.Element)".
 * This fix makes a more specific addParagraph(Paragraph) that does not confuse the native compiler.
 */
@TargetClass(TreeTablePDFExporter.class)
public final class SubstituteTreeTablePdfExporter {

    @Substitute
    protected void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.addParagraph(new Paragraph(" "));
        }
    }
}
