// https://github.com/648540858/wvp-GB28181-pro/tree/ed2f104529053a7215e138581fe8d8dd2eb665e9/src/main/java/com/genersoft/iot/vmp/conf/security/JwtAuthenticationFilter.java#L35-L111
public class TempClass {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 忽略登录请求的token验证
        String requestURI = request.getRequestURI();
        if ((requestURI.startsWith("/doc.html") || requestURI.startsWith("/swagger-ui") ) && !userSetting.getDocEnable()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (requestURI.equalsIgnoreCase("/api/user/login")) {
            chain.doFilter(request, response);
            return;
        }

        if (!userSetting.getInterfaceAuthentication()) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(null, null, new ArrayList<>() );
            SecurityContextHolder.getContext().setAuthentication(token);
            chain.doFilter(request, response);
            return;
        }



        String jwt = request.getHeader(JwtUtils.getHeader());
        // 这里如果没有jwt，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以是可以放行的
        // 没有jwt相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口

        // websocket 鉴权信息默认存储在这里
        String secWebsocketProtocolHeader = request.getHeader(WSHeader);
        if (StringUtils.isBlank(jwt)) {

            if (secWebsocketProtocolHeader != null) {
                jwt = secWebsocketProtocolHeader;
                response.setHeader(WSHeader, secWebsocketProtocolHeader);
            }else {
                jwt = request.getParameter(JwtUtils.getHeader());
            }
            if (StringUtils.isBlank(jwt)) {
                jwt = request.getHeader(JwtUtils.getApiKeyHeader());
                if (StringUtils.isBlank(jwt)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        JwtUser jwtUser = JwtUtils.verifyToken(jwt);
        String username = jwtUser.getUserName();
        // TODO 处理各个状态
        switch (jwtUser.getStatus()){
            case EXPIRED:
                response.setStatus(400);
                chain.doFilter(request, response);
                // 异常
                return;
            case EXCEPTION:
                // 过期
                response.setStatus(400);
                chain.doFilter(request, response);
                return;
            case EXPIRING_SOON:
                // 即将过期
//                return;
            default:
        }
        // 构建UsernamePasswordAuthenticationToken,这里密码为null，是因为提供了正确的JWT,实现自动登录
        User user = new User();
        user.setId(jwtUser.getUserId());
        user.setUsername(jwtUser.getUserName());
        user.setPassword(jwtUser.getPassword());
        Role role = new Role();
        role.setId(jwtUser.getRoleId());
        user.setRole(role);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, jwtUser.getPassword(), new ArrayList<>() );
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }

}