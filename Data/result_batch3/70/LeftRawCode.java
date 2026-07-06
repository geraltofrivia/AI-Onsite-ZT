// https://github.com/Querz/mcaselector/tree/0accb627453cd01a9b470eaace54b9caaefc831d/src/main/java/net/querz/mcaselector/filter/filters/StructureFilter.java#L25-L46
public class TempClass {
	@Override
	public boolean contains(List<String> value, ChunkData data) {
		if (data.region() == null || data.region().getData() == null) {
			return false;
		}
		CompoundTag references = VersionHandler.getImpl(data, ChunkFilter.Structures.class).getStructureReferences(data);
		if (references == null) {
			return false;
		}

		main:
		for (String name : value) {
			for (String alt : StructureRegistry.getAlts(name)) {
				LongArrayTag longArrayStructure = references.getLongArrayTag(alt);
				if (longArrayStructure != null && !longArrayStructure.isEmpty()) {
					continue main;
				}
			}
			return false;
		}
		return true;
	}

}