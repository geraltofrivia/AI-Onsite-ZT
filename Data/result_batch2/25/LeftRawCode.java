// https://github.com/microsoft/typespec/tree/2bccd0226a134214538acb2eb6af04a5626858d5/packages/http-client-java/generator/http-client-generator-core/src/main/java/com/microsoft/typespec/http/client/generator/core/preprocessor/namer/CodeNamer.java#L95-L115
public class TempClass {
    public static String toCamelCase(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }

        // Remove leading underscores.
        if (name.charAt(0) == '_') {
            return toCamelCase(name.substring(1));
        }

        String[] splits = CASE_SPLIT.split(name);
        if (splits.length == 0) {
            return "";
        }

        splits[0] = formatCase(splits[0], true);
        for (int i = 1; i != splits.length; i++) {
            splits[i] = formatCase(splits[i], false);
        }
        return String.join("", splits);
    }

}