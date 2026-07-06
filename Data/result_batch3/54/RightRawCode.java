// https://github.com/adityachandelgit/BookLore/tree/f3f59155edbe53b3ba42d314467f980f09e48c8b/booklore-api/src/main/java/com/adityachandel/booklore/config/security/DualJwtAuthenticationFilter.java#L49-L80
public class TempClass {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = extractToken(request);

        String path = request.getRequestURI();

        if (path.startsWith("/api/v1/opds/") || path.equals("/api/v1/auth/refresh")) {
            chain.doFilter(request, response);
            return;
        }

        if (token == null) {
            chain.doFilter(request, response);
            return;
        }
        try {
            if (jwtUtils.validateToken(token)) {
                authenticateLocalUser(token, request);
            } else if (appSettingService.getAppSettings().isOidcEnabled()) {
                authenticateOidcUser(token, request);
            } else {
                log.debug("OIDC is disabled and token is invalid. Rejecting request.");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } catch (Exception ex) {
            log.error("Authentication error: {}", ex.getMessage(), ex);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(request, response);
    }

}