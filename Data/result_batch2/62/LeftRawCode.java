// https://github.com/648540858/wvp-GB28181-pro/tree/ed2f104529053a7215e138581fe8d8dd2eb665e9/src/main/java/com/genersoft/iot/vmp/conf/security/WebSecurityConfig.java#L133-L154
public class TempClass {
    CorsConfigurationSource configurationSource() {
        // 配置跨域
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setMaxAge(3600L);
        if (userSetting.getAllowedOrigins() != null && !userSetting.getAllowedOrigins().isEmpty()) {
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setAllowedOrigins(userSetting.getAllowedOrigins());
        }else {
            // 在SpringBoot 2.4及以上版本处理跨域时，遇到错误提示：当allowCredentials为true时，allowedOrigins不能包含特殊值"*"。
            // 解决方法是明确指定allowedOrigins或使用allowedOriginPatterns。
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.addAllowedOriginPattern(CorsConfiguration.ALL); // 默认全部允许所有跨域
        }

        corsConfiguration.setExposedHeaders(Arrays.asList(JwtUtils.getHeader()));

        UrlBasedCorsConfigurationSource url = new UrlBasedCorsConfigurationSource();
        url.registerCorsConfiguration("/**", corsConfiguration);
        return url;
    }

}