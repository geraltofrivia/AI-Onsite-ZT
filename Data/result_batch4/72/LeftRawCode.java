// https://github.com/scribejava/scribejava/tree/8970e8eeb0aff840d0b223890e9c392ee19218f7/scribejava-apis/src/main/java/com/github/scribejava/apis/instagram/InstagramAccessTokenErrorResponse.java#L45-L63
public class TempClass {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        final InstagramAccessTokenErrorResponse that = (InstagramAccessTokenErrorResponse) obj;
        if (!Objects.equals(errorMessage, that.getErrorMessage())) {
            return false;
        }
        return code == that.code && Objects.equals(errorType, that.errorType)
                && Objects.equals(response, that.response);
    }

}