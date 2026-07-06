// https://github.com/HotswapProjects/HotswapAgent/tree/1074e9aaed7659654270faae951875cc443379c9/hotswap-agent-core/src/main/java/org/hotswap/agent/util/spring/path/AntPathMatcher.java#L336-L359
public class TempClass {
    protected String[] tokenizePattern(String pattern) {
        String[] tokenized = null;
        Boolean cachePatterns = this.cachePatterns;
        if (cachePatterns == null || cachePatterns.booleanValue()) {
            tokenized = this.tokenizedPatternCache.get(pattern);
        }
        if (tokenized == null) {
            tokenized = tokenizePath(pattern);
            if (cachePatterns == null && this.tokenizedPatternCache.size() >= CACHE_TURNOFF_THRESHOLD) {
                // Try to adapt to the runtime situation that we're
                // encountering:
                // There are obviously too many different patterns coming in
                // here...
                // So let's turn off the cache since the patterns are unlikely
                // to be reoccurring.
                deactivatePatternCache();
                return tokenized;
            }
            if (cachePatterns == null || cachePatterns.booleanValue()) {
                this.tokenizedPatternCache.put(pattern, tokenized);
            }
        }
        return tokenized;
    }

}