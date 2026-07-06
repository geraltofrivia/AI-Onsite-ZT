// https://github.com/cabaletta/baritone/tree/c1aa2012b99172c033c2a72bf66e334e847890c1/src/main/java/baritone/utils/BlockStateInterface.java#L97-L140
public class TempClass {
    public BlockState get0(int x, int y, int z) { // Mickey resigned
        y -= world.dimensionType().minY();
        // Invalid vertical position
        if (y < 0 || y >= world.dimensionType().height()) {
            return AIR;
        }

        if (useTheRealWorld) {
            LevelChunk cached = prev;
            // there's great cache locality in block state lookups
            // generally it's within each movement
            // if it's the same chunk as last time
            // we can just skip the mc.world.getChunk lookup
            // which is a Long2ObjectOpenHashMap.get
            // see issue #113
            if (cached != null && cached.getPos().x == x >> 4 && cached.getPos().z == z >> 4) {
                return getFromChunk(cached, x, y, z);
            }
            LevelChunk chunk = provider.getChunk(x >> 4, z >> 4, ChunkStatus.FULL, false);
            if (chunk != null && !chunk.isEmpty()) {
                prev = chunk;
                return getFromChunk(chunk, x, y, z);
            }
        }
        // same idea here, skip the Long2ObjectOpenHashMap.get if at all possible
        // except here, it's 512x512 tiles instead of 16x16, so even better repetition
        CachedRegion cached = prevCached;
        if (cached == null || cached.getX() != x >> 9 || cached.getZ() != z >> 9) {
            if (worldData == null) {
                return AIR;
            }
            CachedRegion region = worldData.cache.getRegion(x >> 9, z >> 9);
            if (region == null) {
                return AIR;
            }
            prevCached = region;
            cached = region;
        }
        BlockState type = cached.getBlock(x & 511, y + world.dimensionType().minY(), z & 511);
        if (type == null) {
            return AIR;
        }
        return type;
    }

}