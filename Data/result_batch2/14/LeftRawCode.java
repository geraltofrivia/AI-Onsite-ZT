// https://github.com/Heeexy/SpringBoot-Shiro-Vue/tree/170e06aec4f118e2cb9ba0ae995f7b341806ddf1/back/src/main/java/com/heeexy/example/config/filter/PermissionAspect.java#L33-L67
public class TempClass {
    @Before("@annotation(com.heeexy.example.config.annotation.RequiresPermissions)")
    public void before(JoinPoint joinPoint) {
        log.debug("开始校验[操作权限]");
        SessionUserInfo userInfo = tokenService.getUserInfo();
        Set<String> myCodes = userInfo.getPermissionList();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        RequiresPermissions a = methodSignature.getMethod().getAnnotation(RequiresPermissions.class);
        String[] perms = a.value();
        log.debug("校验权限code: {}", Arrays.toString(perms));
        log.debug("用户已有权限: {}", myCodes);
        //5.对比[要求]的code和[用户实际拥有]的code
        if (a.logical() == Logical.AND) {
            //必须包含要求的每个权限
            for (String perm : perms) {
                if (!myCodes.contains(perm)) {
                    log.warn("用户缺少权限 code : {}", perm);
                    throw new UnauthorizedException();//抛出[权限不足]的异常
                }
            }
        } else {
            //多个权限只需包含其中一种即可
            boolean flag = false;
            for (String perm : perms) {
                if (myCodes.contains(perm)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                log.warn("用户缺少权限 code= : {} (任意有一种即可)", Arrays.toString(perms));
                throw new UnauthorizedException();//抛出[权限不足]的异常
            }
        }
    }

}