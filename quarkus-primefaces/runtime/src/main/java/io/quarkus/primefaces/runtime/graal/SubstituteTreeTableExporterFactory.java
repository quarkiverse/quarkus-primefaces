package io.quarkus.primefaces.runtime.graal;

import javax.faces.FacesException;

import org.primefaces.component.export.Exporter;
import org.primefaces.component.export.ExporterOptions;
import org.primefaces.component.export.ExporterType;
import org.primefaces.component.treetable.TreeTable;
import org.primefaces.component.treetable.export.TreeTableCSVExporter;
import org.primefaces.component.treetable.export.TreeTableExcelExporter;
import org.primefaces.component.treetable.export.TreeTableExcelXExporter;
import org.primefaces.component.treetable.export.TreeTableExcelXStreamExporter;
import org.primefaces.component.treetable.export.TreeTableExporterFactory;
import org.primefaces.component.treetable.export.TreeTableXMLExporter;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(TreeTableExporterFactory.class)
public final class SubstituteTreeTableExporterFactory {

    @Substitute
    public static Exporter<TreeTable> getExporter(String type, ExporterOptions options) {
        Exporter<TreeTable> exporter = null;

        try {
            ExporterType exporterType = ExporterType.valueOf(type.toUpperCase());

            switch (exporterType) {
                case XLS:
                    exporter = new TreeTableExcelExporter();
                    break;

                case PDF:
                    throw new UnsupportedOperationException("PDF exporting is not supported in native mode");

                case CSV:
                    exporter = new TreeTableCSVExporter();
                    break;

                case XML:
                    exporter = new TreeTableXMLExporter();
                    break;

                case XLSX:
                    exporter = new TreeTableExcelXExporter();
                    break;
                case XLSXSTREAM:
                    exporter = new TreeTableExcelXStreamExporter();
                    break;

            }
        } catch (IllegalArgumentException e) {
            throw new FacesException(e);
        }

        return exporter;
    }
}
