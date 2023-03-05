package io.quarkus.primefaces.runtime.graal;

import jakarta.faces.FacesException;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.export.DataTableCSVExporter;
import org.primefaces.component.datatable.export.DataTableExcelExporter;
import org.primefaces.component.datatable.export.DataTableExcelXExporter;
import org.primefaces.component.datatable.export.DataTableExcelXStreamExporter;
import org.primefaces.component.datatable.export.DataTableExporterFactory;
import org.primefaces.component.datatable.export.DataTableXMLExporter;
import org.primefaces.component.export.Exporter;
import org.primefaces.component.export.ExporterOptions;
import org.primefaces.component.export.ExporterType;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(DataTableExporterFactory.class)
public final class SubstituteDataTableExporterFactory {

    @Substitute
    public static Exporter<DataTable> getExporter(String type, ExporterOptions options) {
        Exporter<DataTable> exporter = null;

        try {
            ExporterType exporterType = ExporterType.valueOf(type.toUpperCase());

            switch (exporterType) {
                case XLS:
                    exporter = new DataTableExcelExporter();
                    break;

                case PDF:
                    throw new UnsupportedOperationException("PDF exporting is not supported in native mode");

                case CSV:
                    exporter = new DataTableCSVExporter();
                    break;

                case XML:
                    exporter = new DataTableXMLExporter();
                    break;

                case XLSX:
                    exporter = new DataTableExcelXExporter();
                    break;
                case XLSXSTREAM:
                    exporter = new DataTableExcelXStreamExporter();
                    break;

            }
        } catch (IllegalArgumentException e) {
            throw new FacesException(e);
        }

        return exporter;
    }
}
