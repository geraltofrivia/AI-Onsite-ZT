// https://github.com/liuyangming/ByteTCC/tree/1355b11548e922c672dced712956a8f65d92a805/bytetcc-supports-dubbo/src/main/java/org/bytesoft/bytetcc/supports/dubbo/internal/CompensableBeanConfigValidator.java#L237-L262
public class TempClass {
	@SuppressWarnings("rawtypes")
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> targetClass = AopUtils.getTargetClass(bean);
		if (targetClass == null) {
			return bean;
		}

		Compensable compensable = targetClass.getAnnotation(Compensable.class);
		if (compensable == null) {
			return bean;
		}

		Field[] fields = targetClass.getDeclaredFields();
		for (int i = 0; fields != null && i < fields.length; i++) {
			Field field = fields[i];
			Reference reference = field.getAnnotation(Reference.class);
			if (reference == null) {
				continue;
			}

			ReferenceBean referenceConfig = new ReferenceBean(reference);
			this.validateReferenceBean(beanName, referenceConfig);
		}

		return bean;
	}

}