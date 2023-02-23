package io.quarkus.primefaces.runtime.graal;

import org.primefaces.component.datatable.export.DataTablePDFExporter;

import com.oracle.svm.core.annotate.Delete;
import com.oracle.svm.core.annotate.TargetClass;

/**
 * OpenLibre PDF library uses Apache FOP and other libs and does not work with native images yet.
 *
 * @see <a href="https://github.com/quarkusio/quarkus/issues/31224">Quarkus AWT Issue</a>
 */
@Delete
@TargetClass(DataTablePDFExporter.class)
public final class DeleteDataTablePdfExporter {
}
