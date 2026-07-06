// https://github.com/Konloch/bytecode-viewer/tree/903ac6a3fd19f1a3047789a91fb35e48d60459fb/src/main/java/the/bytecode/club/bytecodeviewer/plugin/strategies/CompiledJavaPluginLaunchStrategy.java#L199-L220
public class TempClass {
        @Override
        public Class<?> findClass(String name) throws ClassNotFoundException
        {
            name = name.replace(".", "/");

            System.out.println("finding " + name);

            if (classCache.containsKey(name))
                return classCache.get(name);

            LoadedNodeData data = nodeCache.get(name);

            if (data != null)
            {
                byte[] bytes = data.bytes;
                Class<?> klass = defineClass(data.node.name.replace("/", "."), bytes, 0, bytes.length);
                classCache.put(name, klass);
                return klass;
            }

            return super.findClass(name);
        }

}