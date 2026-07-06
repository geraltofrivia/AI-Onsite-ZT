// https://github.com/xuchengsheng/spring-reading/tree/9911cbb4df107d3a1d87214b137af9871e21525f/spring-interface/spring-interface-mergedBeanDefinitionPostProcessor/src/main/java/com/xcs/spring/config/MyMergedBeanDefinitionPostProcessor.java#L38-L56
public class TempClass {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (metadata.containsKey(beanName)) {
            Map<Field, String> defaultValues = metadata.get(beanName);
            for (Map.Entry<Field, String> entry : defaultValues.entrySet()) {
                Field field = entry.getKey();
                String value = entry.getValue();
                try {
                    field.setAccessible(true);
                    if (field.get(bean) == null) {
                        field.set(bean, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

}