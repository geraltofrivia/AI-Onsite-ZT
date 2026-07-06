// https://github.com/metersphere/metersphere/tree/f043cdd28b46b186e9dc28cfd5d9b1886165bcd5/backend/services/system-setting/src/main/java/io/metersphere/system/security/CheckOwnerAspect.java#L43-L94
public class TempClass {
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取参数对象数组
        Object[] args = joinPoint.getArgs();
        CheckOwner checkOwner = method.getAnnotation(CheckOwner.class);
        long count = SessionUtils.getUser().getUserRoles()
                .stream()
                .filter(g -> StringUtils.equalsIgnoreCase(g.getId(), InternalUserRole.ADMIN.getValue()))
                .count();
        if (count > 0) {
            return;
        }
        // 操作内容
        //获取方法参数名
        String[] params = discoverer.getParameterNames(method);
        //将参数纳入Spring管理
        EvaluationContext context = new StandardEvaluationContext();
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], args[len]);
        }

        String resourceId = checkOwner.resourceId();
        String resourceType = checkOwner.resourceType();
        String relationType = checkOwner.relationType();
        Expression titleExp = parser.parseExpression(resourceId);
        Object v = titleExp.getValue(context, Object.class);
        // 归属组织的资源
        if (orgResources.contains(resourceType)) {
            handleOrganizationResource(v, resourceType);
        }
        // 组织自身
        else if (StringUtils.equals(resourceType, "organization")) {
            handleOrganization(v);
        }
        // 中间表
        else if (StringUtils.isNotBlank(relationType)) {
            handleProjectResource(v, resourceType, relationType);
        }
        // 归属项目的资源
        else {
            handleProjectResource(v, resourceType);
        }
    }

    private void handleOrganization(Object v) {
        if (v instanceof String id) {
            if (!extCheckOwnerMapper.checkoutOrganization(SessionUtils.getUserId(), List.of(id))) {

}