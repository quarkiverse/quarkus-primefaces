package io.quarkus.primefaces.runtime.graal;

import org.primefaces.component.fileupload.CommonsFileUploadDecoder;
import org.primefaces.model.file.CommonsUploadedFile;
import org.primefaces.webapp.MultipartRequest;
import org.primefaces.webapp.filter.FileUploadFilter;

import com.oracle.svm.core.annotate.Delete;
import com.oracle.svm.core.annotate.TargetClass;

/**
 * Apache Commons FileUpload is not used in GraalVM mode only native JSF servlet uploading.
 */
public final class DeleteApacheCommonsFileUpload {
}

@Delete
@TargetClass(CommonsFileUploadDecoder.class)
final class DeleteCommonsFileUploadDecoder {
}

@Delete
@TargetClass(FileUploadFilter.class)
final class DeleteFileUploadFilter {
}

@Delete
@TargetClass(CommonsUploadedFile.class)
final class DeleteCommonsUploadedFile {
}

@Delete
@TargetClass(MultipartRequest.class)
final class DeleteMultipartRequest {
}
