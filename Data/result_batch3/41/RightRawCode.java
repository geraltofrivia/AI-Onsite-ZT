// https://github.com/alibaba/yugong/tree/94a5888688415510a20bd72d246cbf93eb1d43f0/src/main/java/com/taobao/yugong/common/utils/compile/JdkCompiler.java#L189-L217
public class TempClass {
        public Iterable<JavaFileObject> list(Location location, String packageName, Set<Kind> kinds, boolean recurse)
                                                                                                                     throws IOException {
            Iterable<JavaFileObject> files = super.list(location, packageName, kinds, recurse);
            List<JavaFileObject> result = new ArrayList<JavaFileObject>();
            for (JavaFileObject file : files) {
                result.add(file);
            }
            if (StandardLocation.CLASS_PATH == location && kinds.contains(JavaFileObject.Kind.CLASS)) {
                for (JavaFileObject file : fileDatas.values()) {
                    if (Kind.CLASS == file.getKind() && file.getName().startsWith(packageName)) {
                        result.add(file);
                    }
                }

                result.addAll(classLoader.getAllFiles());
            }

            if (StandardLocation.SOURCE_PATH == location && kinds.contains(JavaFileObject.Kind.SOURCE)) {
                for (JavaFileObject file : fileDatas.values()) {
                    if (Kind.SOURCE == file.getKind() && file.getName().startsWith(packageName)) {
                        result.add(file);
                    }
                }
            }

            return result;
        }

        private URI clasURI(Location location, String packageName, String relativeName) {

}