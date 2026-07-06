// https://github.com/crossoverJie/SSM/tree/1c14460a95c23e2e57fb3fd25b445a0343cad5cf/SSM-WEB/src/main/java/com/crossoverJie/shiro/MyRealm.java#L33-L44
public class TempClass {
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString() ;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
        Set<String> roleName = userService.findRoles(username) ;
        Set<String> permissions = userService.findPermissions(username) ;
        info.setRoles(roleName);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 首先执行这个登录验证

}