// https://github.com/openboard-team/openboard/tree/c3772cd56e770975ea5570db903f93b199de8b32/app/src/main/java/org/dslul/openboard/inputmethod/latin/utils/JsonUtils.java#L75-L99
public class TempClass {
    public static String listToJsonStr(final List<Object> list) {
        if (list == null || list.isEmpty()) {
            return EMPTY_STRING;
        }
        final StringWriter sw = new StringWriter();
        final JsonWriter writer = new JsonWriter(sw);
        try {
            writer.beginArray();
            for (final Object o : list) {
                writer.beginObject();
                if (o instanceof Integer) {
                    writer.name(INTEGER_CLASS_NAME).value((Integer)o);
                } else if (o instanceof String) {
                    writer.name(STRING_CLASS_NAME).value((String)o);
                }
                writer.endObject();
            }
            writer.endArray();
            return sw.toString();
        } catch (final IOException e) {
        } finally {
            close(writer);
        }
        return EMPTY_STRING;
    }

}