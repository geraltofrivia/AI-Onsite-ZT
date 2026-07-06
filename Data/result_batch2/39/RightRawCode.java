// https://github.com/HotswapProjects/HotswapAgent/tree/1074e9aaed7659654270faae951875cc443379c9/hotswap-agent-core/src/main/java/org/hotswap/agent/util/spring/path/AntPathMatcher.java#L407-L430
public class TempClass {
    protected AntPathStringMatcher getStringMatcher(String pattern) {
        AntPathStringMatcher matcher = null;
        Boolean cachePatterns = this.cachePatterns;
        if (cachePatterns == null || cachePatterns.booleanValue()) {
            matcher = this.stringMatcherCache.get(pattern);
        }
        if (matcher == null) {
            matcher = new AntPathStringMatcher(pattern, this.caseSensitive);
            if (cachePatterns == null && this.stringMatcherCache.size() >= CACHE_TURNOFF_THRESHOLD) {
                // Try to adapt to the runtime situation that we're
                // encountering:
                // There are obviously too many different patterns coming in
                // here...
                // So let's turn off the cache since the patterns are unlikely
                // to be reoccurring.
                deactivatePatternCache();
                return matcher;
            }
            if (cachePatterns == null || cachePatterns.booleanValue()) {
                this.stringMatcherCache.put(pattern, matcher);
            }
        }
        return matcher;
    }

}