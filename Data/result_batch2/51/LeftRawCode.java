// https://github.com/Querz/mcaselector/tree/0accb627453cd01a9b470eaace54b9caaefc831d/src/main/java/net/querz/mcaselector/version/mapping/util/CollectionAdapter.java#L13-L29
public class TempClass {
	@Override
	public JsonElement serialize(Collection<?> src, Type typeOfSrc, JsonSerializationContext context) {
		if (src == null || src.isEmpty()) {
			return null;
		}
		JsonArray array = new JsonArray();
		for (Object o : src) {
			array.add(context.serialize(o));
		}
		new JsonArrayList(array).sort((a, b) -> {
			if (a.isJsonPrimitive() && b.isJsonPrimitive()) {
				return a.getAsString().compareTo(b.getAsString());
			}
			return 0;
		});
		return array;
	}

}