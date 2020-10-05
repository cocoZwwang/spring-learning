package per.cocoadel.learning.srping.application.state.run;

import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalysisReporter;

public class ConsoleFailureAnalysisReporter implements FailureAnalysisReporter {

    @Override
    public void report(FailureAnalysis analysis) {
        System.out.printf("故障描述：%s\n处理建议：%s\n异常堆栈：%s",
                analysis.getDescription(),analysis.getAction(),analysis.getCause());
    }
}
