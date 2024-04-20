package io.quarkus.primefaces.runtime.graal;

import org.primefaces.component.datatable.export.DataTablePDFExporter;

import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

/**
 * See notes above each method
 */
@TargetClass(DataTablePDFExporter.class)
public final class SubstituteDataTablePdfExporter {

    /**
     * iText Paragraph.add(object) is ambiguous and is confusing the native parser with the error:
     * "Discovered unresolved method during parsing: com.lowagie.text.Paragraph.add(com.lowagie.text.Element)".
     * This fix makes a more specific addParagraph(Paragraph) that does not confuse the native compiler.
     */
    @Substitute
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.addParagraph(new Paragraph(" "));
        }
    }

    /**
     * iText PdfPTable.addCell(object) is ambiguous and is confusing the native parser with the error:
     * "Discovered unresolved method during parsing: com.lowagie.text.PdfPTable.addCell(com.lowagie.text.pdf.PdfPCell)".
     * This fix makes a more specific addCellAsCell(Paragraph) that does not confuse the native compiler.
     */
    @Substitute
    protected void addCell(PdfPTable table, PdfPCell cell) {
        table.addCellAsCell(cell);
    }
}
