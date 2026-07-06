// https://github.com/weixiansen574/HybridFileXfer/tree/7de43517d8dd5cc2ccc24ccb11060b1ee68dd952/HybridFileXfer-Android/app/src/main/java/top/weixiansen574/async/BackstageTask.java#L75-L88
public class TempClass {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == Object.class){
                return method.invoke(proxy,args);
            }
            TaskManger.postOnUiThread(() -> {
                try {
                    method.invoke(evHandler,args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
            return null;
        }

}