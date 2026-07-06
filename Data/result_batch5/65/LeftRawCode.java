// https://github.com/GeyserMC/Geyser/tree/4746651aeaef5e4d58a58b7bf17125e388d87d7a/core/src/main/java/org/geysermc/geyser/session/cache/ChunkCache.java#L101-L122
public class TempClass {
    public int getBlockAt(int x, int y, int z) {
        if (!cache) {
            return Block.JAVA_AIR_ID;
        }

        GeyserChunk column = this.getChunk(x >> 4, z >> 4);
        if (column == null) {
            return Block.JAVA_AIR_ID;
        }

        if (y < minY || ((y - minY) >> 4) > column.sections().length - 1) {
            // Y likely goes above or below the height limit of this world
            return Block.JAVA_AIR_ID;
        }

        DataPalette chunk = column.sections()[(y - minY) >> 4];
        if (chunk != null) {
            return chunk.get(x & 0xF, y & 0xF, z & 0xF);
        }

        return Block.JAVA_AIR_ID;
    }

}