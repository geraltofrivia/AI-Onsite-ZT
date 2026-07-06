// https://github.com/iterate-ch/cyberduck/tree/d0cc808faa1d888eccb7f939fc0924b129d71792/core/src/main/java/ch/cyberduck/core/OAuthTokens.java#L72-L91
public class TempClass {
    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final OAuthTokens that = (OAuthTokens) o;
        if(!Objects.equals(accessToken, that.accessToken)) {
            return false;
        }
        if(!Objects.equals(refreshToken, that.refreshToken)) {
            return false;
        }
        if(!Objects.equals(idToken, that.idToken)) {
            return false;
        }
        return true;
    }

}