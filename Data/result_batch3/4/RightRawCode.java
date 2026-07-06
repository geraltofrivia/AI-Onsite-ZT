// https://github.com/Exrick/xboot/tree/5277af0ea7db3cf085f8ef3be21329df5bb12cd9/xboot-fast/src/main/java/cn/exrick/xboot/modules/open/controller/Oauth2Controller.java#L121-L218
public class TempClass {
    public Result<Object> token(@ApiParam("授权类型") @RequestParam String grant_type,
                                @ApiParam("客户端id") @RequestParam String client_id,
                                @ApiParam("客户端秘钥") @RequestParam String client_secret,
                                @ApiParam("认证返回的code") @RequestParam(required = false) String code,
                                @ApiParam("刷新token") @RequestParam(required = false) String refresh_token,
                                @ApiParam("成功授权后回调地址") @RequestParam(required = false) String redirect_uri) {

        Client client = getClient(client_id);

        // 判断clientSecret
        if (!client.getClientSecret().equals(client_secret)) {
            return ResultUtil.error("client_secret不正确");
        }
        Oauth2TokenInfo tokenInfo = null;
        if (OAuthConstant.AUTHORIZATION_CODE.equals(grant_type)) {
            // 判断回调地址
            if (!client.getRedirectUri().equals(redirect_uri)) {
                return ResultUtil.error("回调地址redirect_uri不正确");
            }
            // 判断code 获取用户信息
            String codeValue = redisTemplate.get(OAuthConstant.OAUTH_CODE_PRE + code);
            if (StrUtil.isBlank(codeValue)) {
                return ResultUtil.error("code已过期");
            }
            tokenInfo = new Gson().fromJson(codeValue, Oauth2TokenInfo.class);
            if (!tokenInfo.getClientId().equals(client_id)) {
                return ResultUtil.error("code不正确");
            }
        } else if (OAuthConstant.REFRESH_TOKEN.equals(grant_type)) {
            // 从refreshToken中获取用户信息
            String refreshTokenValue = redisTemplate.get(OAuthConstant.OAUTH_TOKEN_INFO_PRE + refresh_token);
            if (StrUtil.isBlank(refreshTokenValue)) {
                return ResultUtil.error("refresh_token已过期");
            }
            tokenInfo = new Gson().fromJson(refreshTokenValue, Oauth2TokenInfo.class);
            if (!tokenInfo.getClientId().equals(client_id)) {
                return ResultUtil.error("refresh_token不正确");
            }
        } else {
            return ResultUtil.error("授权类型grant_type不正确");
        }

        String token = null, refreshToken = null;
        Long expiresIn = null;
        String tokenKey = OAuthConstant.OAUTH_TOKEN_PRE + tokenInfo.getUsername() + ":" + client_id,
                refreshKey = OAuthConstant.OAUTH_REFRESH_TOKEN_PRE + tokenInfo.getUsername() + ":" + client_id;
        if (OAuthConstant.AUTHORIZATION_CODE.equals(grant_type)) {
            // 生成token模式
            String oldToken = redisTemplate.get(tokenKey);
            String oldRefreshToken = redisTemplate.get(refreshKey);
            if (StrUtil.isNotBlank(oldToken) && StrUtil.isNotBlank(oldRefreshToken)) {
                // 旧token
                token = oldToken;
                refreshToken = oldRefreshToken;
                expiresIn = redisTemplate.getExpire(OAuthConstant.OAUTH_TOKEN_INFO_PRE + token, TimeUnit.SECONDS);
            } else {
                // 新生成 30天过期
                String newToken = IdUtil.simpleUUID();
                String newRefreshToken = IdUtil.simpleUUID();
                redisTemplate.set(tokenKey, newToken, 30L, TimeUnit.DAYS);
                redisTemplate.set(refreshKey, newRefreshToken, 30L, TimeUnit.DAYS);
                // 新token中存入用户信息
                redisTemplate.set(OAuthConstant.OAUTH_TOKEN_INFO_PRE + newToken, new Gson().toJson(tokenInfo), 30L, TimeUnit.DAYS);
                redisTemplate.set(OAuthConstant.OAUTH_TOKEN_INFO_PRE + newRefreshToken, new Gson().toJson(tokenInfo), 30L, TimeUnit.DAYS);
                token = newToken;
                refreshToken = newRefreshToken;
                expiresIn = redisTemplate.getExpire(OAuthConstant.OAUTH_TOKEN_INFO_PRE + token, TimeUnit.SECONDS);
            }
        } else if (OAuthConstant.REFRESH_TOKEN.equals(grant_type)) {
            // 刷新token模式 生成新token 30天过期
            String newToken = IdUtil.simpleUUID();
            String newRefreshToken = IdUtil.simpleUUID();
            redisTemplate.set(tokenKey, newToken, 30L, TimeUnit.DAYS);
            redisTemplate.set(refreshKey, newRefreshToken, 30L, TimeUnit.DAYS);
            // 新token中存入用户信息
            redisTemplate.set(OAuthConstant.OAUTH_TOKEN_INFO_PRE + newToken, new Gson().toJson(tokenInfo), 30L, TimeUnit.DAYS);
            redisTemplate.set(OAuthConstant.OAUTH_TOKEN_INFO_PRE + newRefreshToken, new Gson().toJson(tokenInfo), 30L, TimeUnit.DAYS);
            token = newToken;
            refreshToken = newRefreshToken;
            expiresIn = redisTemplate.getExpire(OAuthConstant.OAUTH_TOKEN_INFO_PRE + token, TimeUnit.SECONDS);
            // 旧refreshToken过期
            redisTemplate.delete(OAuthConstant.OAUTH_TOKEN_INFO_PRE + refresh_token);
        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("access_token", token);
        map.put("expires_in", expiresIn);
        map.put("refresh_token", refreshToken);
        return ResultUtil.data(map);
    }

    @RequestMapping(value = "/authorized", method = RequestMethod.POST)
    @ApiOperation(value = "已认证过获取code/单点登录实现")
    public Result<Object> authorized(@ApiParam("客户端id") @RequestParam String client_id,
                                     @ApiParam("成功授权后回调地址") @RequestParam String redirect_uri,
                                     @ApiParam("客户端状态值") @RequestParam String state) {

        Client client = getClient(client_id);

}