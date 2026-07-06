// https://github.com/theonedev/onedev/tree/1cdca661bd5d1c833261a8d82232b4097b1d6c0c/server-core/src/main/java/org/apache/wicket/util/string/Strings.java#L1210-L1251
public class TempClass {
	public static String toString(final Throwable throwable)
	{
		if (throwable != null)
		{
			List<Throwable> al = new ArrayList<>();
			Throwable cause = throwable;
			al.add(cause);
			while ((cause.getCause() != null) && (cause != cause.getCause()))
			{
				cause = cause.getCause();
				al.add(cause);
			}

			AppendingStringBuffer sb = new AppendingStringBuffer(256);
			// first print the last cause
			int length = al.size() - 1;
			cause = al.get(length);
			if (throwable instanceof RuntimeException)
			{
				sb.append("Message: ");
				sb.append(throwable.getMessage());
				sb.append("\n\n");
			}
			sb.append("Root cause:\n\n");
			outputThrowable(cause, sb, false);

			if (length > 0)
			{
				sb.append("\n\nComplete stack:\n\n");
				for (int i = 0; i < length; i++)
				{
					outputThrowable(al.get(i), sb, true);
					sb.append('\n');
				}
			}
			return sb.toString();
		}
		else
		{
			return "<Null Throwable>";
		}
	}

}