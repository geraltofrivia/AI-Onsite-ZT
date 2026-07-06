// https://github.com/scribejava/scribejava/tree/8970e8eeb0aff840d0b223890e9c392ee19218f7/scribejava-core/src/main/java/com/github/scribejava/core/model/OAuth1AccessToken.java#L48-L64
public class TempClass {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OAuth1AccessToken other = (OAuth1AccessToken) obj;
        if (!Objects.equals(getToken(), other.getToken())) {
            return false;
        }
        return Objects.equals(getTokenSecret(), other.getTokenSecret());
    }

}