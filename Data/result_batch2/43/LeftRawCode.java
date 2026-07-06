// https://github.com/wuyouzhuguli/SpringAll/tree/614d2578d9495acf53cc02f2dee9c6131cc5e51a/11.Spring-Boot-Shiro-Authentication/src/main/java/com/springboot/controller/LoginController.java#L29-L47
public class TempClass {
	@PostMapping("/login")
	@ResponseBody
	public ResponseBo login(String username, String password) {
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return ResponseBo.ok();
		} catch (UnknownAccountException e) {
			return ResponseBo.error(e.getMessage());
		} catch (IncorrectCredentialsException e) {
			return ResponseBo.error(e.getMessage());
		} catch (LockedAccountException e) {
			return ResponseBo.error(e.getMessage());
		} catch (AuthenticationException e) {
			return ResponseBo.error("认证失败！");
		}
	}

}