// https://github.com/freeplane/freeplane/tree/095dd404779101b2064a2480956dfa5cbf8bbc30/freeplane_plugin_script/src/main/java/org/freeplane/plugin/script/proxy/Convertible.java#L241-L252
public class TempClass {
	public static String toString(Object value) {
		if (value == null)
			return null;
		else if (value.getClass().equals(String.class))
			return (String) value;
		else if (value instanceof Date)
			return FormattedDate.toStringISO(((Date) value));
		else if (value instanceof Calendar)
			return FormattedDate.toStringISO(((Calendar) value).getTime());
		else
			return value.toString();
	}

}