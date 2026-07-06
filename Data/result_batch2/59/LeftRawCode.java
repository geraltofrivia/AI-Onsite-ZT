// https://github.com/mockito/mockito/tree/c8a698b8a2178e54910a688f97c179525b406aae/mockito-core/src/main/java/org/mockito/internal/util/reflection/GenericMetadataSupport.java#L275-L294
public class TempClass {
    public GenericMetadataSupport resolveGenericReturnType(Method method) {
        Type genericReturnType = method.getGenericReturnType();
        // logger.log("Method '" + method.toGenericString() + "' has return type : " +
        // genericReturnType.getClass().getInterfaces()[0].getSimpleName() + " : " +
        // genericReturnType);

        int arity = 0;
        while (genericReturnType instanceof GenericArrayType) {
            arity++;
            genericReturnType = ((GenericArrayType) genericReturnType).getGenericComponentType();
        }

        GenericMetadataSupport genericMetadataSupport =
                resolveGenericType(genericReturnType, method);
        if (arity == 0) {
            return genericMetadataSupport;
        } else {
            return new GenericArrayReturnType(genericMetadataSupport, arity);
        }
    }

}