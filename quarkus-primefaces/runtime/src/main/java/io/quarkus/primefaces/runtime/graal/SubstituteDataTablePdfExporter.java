package io.quarkus.primefaces.runtime.graal;

import org.primefaces.component.datatable.export.DataTablePDFExporter;

import com.lowagie.text.Paragraph;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

/**
 * iText Paragraph.add(object) is ambiguous and is confusing the native parser with the error:
 * "Discovered unresolved method during parsing: com.lowagie.text.Paragraph.add(com.lowagie.text.Element)".
 * This fix makes a more specific addParagraph(Paragraph) that does not confuse the native compiler.
 */
@TargetClass(DataTablePDFExporter.class)
public final class SubstituteDataTablePdfExporter {

    @Substitute
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.addParagraph(new Paragraph(" "));
        }
    }
}