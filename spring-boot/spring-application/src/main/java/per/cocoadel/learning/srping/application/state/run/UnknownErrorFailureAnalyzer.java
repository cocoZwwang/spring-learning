package per.cocoadel.learning.srping.application.state.run;

import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;


public class UnknownErrorFailureAnalyzer implements FailureAnalyzer {
    @Override
    public FailureAnalysis analyze(Throwable failure) {
        if(failure instanceof UnknownError){
            return new FailureAnalysis("未知错误","请尝试重启",failure);
        }
        return null;

    }
}
