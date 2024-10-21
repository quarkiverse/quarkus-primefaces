package io.quarkus.primefaces.runtime.graal;

import java.io.IOException;
import java.io.Writer;

import org.primefaces.util.ChartUtils;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(ChartUtils.class)
final class SubstituteChartUtils {

    @Substitute
    public static void writeDataValue(Writer fsw, String optionName, Object value, boolean hasComma) throws IOException {
        throw new IllegalArgumentException("ChartUtils is deprecated and should not be used");
    }
}