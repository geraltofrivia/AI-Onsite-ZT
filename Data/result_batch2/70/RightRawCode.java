// https://github.com/risesoft-y9/Digital-Infrastructure/tree/ed8caa40c8bcd856fea4b328df5d7e4c562403da/y9-digitalbase-module/y9-module-sso/risenet-y9boot-webapp-sso-jpa/src/main/java/y9/util/Y9Context.java#L129-L156
public class TempClass {
            if (StringUtils.isEmpty(addr) || "unknown".equalsIgnoreCase(addr)) {
                addr = request.getHeader(header);
            } else {
                break;
            }
        }

        if (StringUtils.isEmpty(addr) || "unknown".equalsIgnoreCase(addr)) {
            addr = request.getRemoteAddr();
        } else {
            int i = addr.indexOf(",");
            if (i > 0) {
                addr = addr.substring(0, i);
            }
        }
        return addr;
    }

    public static String getLogoutUrl(String path) {
        String logoutUrl = path + servletContext.getContextPath();
        return logoutUrl;
    }

    public static String getProperty(String key) {
        return environment.getProperty(key);
    }

    public static String getRealPath(String path) {

}