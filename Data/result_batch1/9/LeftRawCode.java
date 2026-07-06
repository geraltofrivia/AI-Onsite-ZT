// https://github.com/theonedev/onedev/tree/1cdca661bd5d1c833261a8d82232b4097b1d6c0c/server-core/src/main/java/org/apache/wicket/util/string/Strings.java#L872-L897
public class TempClass {
	public static String[] split(final String s, final char c)
	{
		if (s == null || s.length() == 0)
		{
			return NO_STRINGS;
		}
		final List<String> strings = new ArrayList<>();
		int pos = 0;
		while (true)
		{
			int next = s.indexOf(c, pos);
			if (next == -1)
			{
				strings.add(s.substring(pos));
				break;
			}
			else
			{
				strings.add(s.substring(pos, next));
			}
			pos = next + 1;
		}
		final String[] result = new String[strings.size()];
		strings.toArray(result);
		return result;
	}

}