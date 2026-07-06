// https://github.com/GeyserMC/Geyser/tree/4746651aeaef5e4d58a58b7bf17125e388d87d7a/bootstrap/bungeecord/src/main/java/org/geysermc/geyser/platform/bungeecord/GeyserBungeeCompressionDisabler.java#L38-L61
public class TempClass {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (!(msg instanceof SetCompression)) {
            // Fixes https://github.com/GeyserMC/Geyser/issues/4281
            // The server may send a LoginDisconnect packet after compression is set.
            if (!compressionDisabled) {
                LengthPrependerAndCompressor compressor = ctx.pipeline().get(LengthPrependerAndCompressor.class);
                if (compressor.isCompress()) {
                    compressor.setCompress(false);
                    compressionDisabled = true;
                }
                if (ctx.pipeline().get("decompress") != null) {
                    ctx.pipeline().remove("decompress");
                    compressionDisabled = true;
                }
            }

            if (msg instanceof LoginSuccess) {
                // We're past the point that compression can be enabled
                ctx.pipeline().remove(this);
            }
            super.write(ctx, msg, promise);
        }
    }

}