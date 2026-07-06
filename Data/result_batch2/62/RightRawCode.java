// https://github.com/halo-dev/halo/tree/395399f078c39a7cd1b4b29509ab95d7ece0a296/application/src/main/java/run/halo/app/security/CorsConfigurer.java#L36-L54
public class TempClass {
    CorsConfigurationSource apiCorsConfigSource() {
        var source = new UrlBasedCorsConfigurationSource();
        // additional CORS configuration
        this.corsOptions.getConfigs().forEach(corsConfig -> source.registerCorsConfiguration(
            corsConfig.getPathPattern(), corsConfig.getConfig().toCorsConfiguration()
        ));

        // default CORS configuration
        var configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedHeaders(
            List.of(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT,
                "X-XSRF-TOKEN", HttpHeaders.COOKIE));
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/api/**", configuration);
        source.registerCorsConfiguration("/apis/**", configuration);
        return source;
    }

}