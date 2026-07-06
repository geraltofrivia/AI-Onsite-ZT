// https://github.com/dataease/dataease/tree/4fca2f1eacbffed4b1984dcbb561a44543f601a3/sdk/common/src/main/java/io/dataease/auth/filter/CommunityTokenFilter.java#L32-L69
public class TempClass {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Long userId = null;
        String token = ServletUtils.getToken();
        TokenUserBO userBO = null;
        if (StringUtils.isNotBlank(token) && ObjectUtils.isNotEmpty(userBO = AuthUtils.getUser()) && ObjectUtils.isNotEmpty(userId = userBO.getUserId()) && !LicenseUtil.licenseValid()) {
            String secret = null;
            if (ObjectUtils.isEmpty(CommonBeanFactory.getBean("loginServer"))) {
                String pwd = SubstituleLoginConfig.getPwd();
                secret = Md5Utils.md5(pwd);
            } else {
                Object apisixCacheManage = CommonBeanFactory.getBean("apisixCacheManage");
                Method method = DeReflectUtil.findMethod(apisixCacheManage.getClass(), "userCacheBO");
                Object o = ReflectionUtils.invokeMethod(method, apisixCacheManage, userId);
                Method pwdMethod = DeReflectUtil.findMethod(o.getClass(), "getPwd");
                Object pwdObj = ReflectionUtils.invokeMethod(pwdMethod, o);
                secret = pwdObj.toString();
            }
            try {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                Verification verification = JWT.require(algorithm).withClaim("uid", userId).withClaim("oid", userBO.getDefaultOid());
                JWTVerifier verifier = verification.build();
                DecodedJWT decode = JWT.decode(token);
                algorithm.verify(decode);
                verifier.verify(token);
            } catch (Exception e) {
                HttpServletResponse res = (HttpServletResponse) servletResponse;
                LogUtil.error(e.getMessage(), e);
                HttpHeaders headers = new HttpHeaders();
                String msg = URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8).replace("+", "%20");
                headers.add(headName, msg);
                sendResponseEntity(res, new ResponseEntity<>(e.getMessage(), headers, HttpStatus.UNAUTHORIZED));
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}