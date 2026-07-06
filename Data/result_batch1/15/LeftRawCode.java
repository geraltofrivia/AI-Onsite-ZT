// https://github.com/scribejava/scribejava/tree/8970e8eeb0aff840d0b223890e9c392ee19218f7/scribejava-core/src/main/java/com/github/scribejava/core/model/OAuth2AccessToken.java#L106-L131
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
        final OAuth2AccessToken other = (OAuth2AccessToken) obj;
        if (!Objects.equals(accessToken, other.getAccessToken())) {
            return false;
        }
        if (!Objects.equals(tokenType, other.getTokenType())) {
            return false;
        }
        if (!Objects.equals(refreshToken, other.getRefreshToken())) {
            return false;
        }
        if (!Objects.equals(scope, other.getScope())) {
            return false;
        }
        return Objects.equals(expiresIn, other.getExpiresIn());
    }

}