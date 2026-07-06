// https://github.com/iterate-ch/cyberduck/tree/d0cc808faa1d888eccb7f939fc0924b129d71792/hubic/src/main/java/ch/cyberduck/core/hubic/HubicAuthenticationResponseHandler.java#L41-L67
public class TempClass {
    @Override
    public AuthenticationResponse handleResponse(final HttpResponse response) throws IOException {
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            Charset charset = HTTP.DEF_CONTENT_CHARSET;
            ContentType contentType = ContentType.get(response.getEntity());
            if(contentType != null) {
                if(contentType.getCharset() != null) {
                    charset = contentType.getCharset();
                }
            }
            try {
                final JsonObject json = JsonParser.parseReader(new InputStreamReader(response.getEntity().getContent(), charset)).getAsJsonObject();
                final String token = json.getAsJsonPrimitive("token").getAsString();
                final String endpoint = json.getAsJsonPrimitive("endpoint").getAsString();
                return new AuthenticationResponse(response, token,
                        Collections.singleton(new Region(null, URI.create(endpoint), null, true)));
            }
            catch(JsonParseException e) {
                throw new IOException(e.getMessage(), e);
            }
        }
        else if(response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED
                || response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            throw new AuthorizationException(new Response(response));
        }
        throw new GenericException(new Response(response));
    }

}