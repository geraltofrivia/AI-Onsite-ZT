// https://github.com/karatelabs/karate/tree/ace1051b5e40410227f46709708513a5b366d0a9/karate-core/src/main/java/com/intuit/karate/core/ScenarioEngine.java#L676-L711
public class TempClass {
    private void httpInvokeWithRetries() {
        int maxRetries = config.getRetryCount();
        int sleep = config.getRetryInterval();
        int retryCount = 0;
        while (true) {
            if (retryCount == maxRetries) {
                throw new KarateException("too many retry attempts: " + maxRetries);
            }
            if (retryCount > 0) {
                try {
                    logger.debug("sleeping before retry #{}", retryCount);
                    Thread.sleep(sleep);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            Variable v;
            try {
                httpInvokeOnce();
                v = evalKarateExpression(requestBuilder.getRetryUntil());
            } catch (Exception e) {
                logger.warn("retry condition evaluation failed: {}", e.getMessage());
                v = Variable.NULL;
            }
            if (v.isTrue()) {
                if (retryCount > 0) {
                    logger.debug("retry condition satisfied");
                }
                break;
            } else {
                logger.debug("retry condition not satisfied: {}", requestBuilder.getRetryUntil());
            }
            retryCount++;
        }
    }

}