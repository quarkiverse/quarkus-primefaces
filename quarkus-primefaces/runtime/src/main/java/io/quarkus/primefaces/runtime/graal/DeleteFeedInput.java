package io.quarkus.primefaces.runtime.graal;

import org.primefaces.component.feedreader.FeedInput;

import com.oracle.svm.core.annotate.Delete;
import com.oracle.svm.core.annotate.TargetClass;

/**
 * Rome RSS library uses JDOM XML parsing and its failing so not worth investigating.
 */
@Delete
@TargetClass(FeedInput.class)
public final class DeleteFeedInput {
}
