// https://github.com/iterate-ch/cyberduck/tree/d0cc808faa1d888eccb7f939fc0924b129d71792/core/src/main/java/ch/cyberduck/core/TemporaryAccessTokens.java#L70-L89
public class TempClass {
    @Override
    public boolean equals(final Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        final TemporaryAccessTokens temporaryAccessTokens = (TemporaryAccessTokens) o;
        if(!Objects.equals(accessKeyId, temporaryAccessTokens.accessKeyId)) {
            return false;
        }
        if(!Objects.equals(secretAccessKey, temporaryAccessTokens.secretAccessKey)) {
            return false;
        }
        if(!Objects.equals(sessionToken, temporaryAccessTokens.sessionToken)) {
            return false;
        }
        return true;
    }

}