// https://github.com/microsoft/malmo/tree/b59258d812a2600c0c615358684fcf660d83455c/Minecraft/src/main/java/com/microsoft/Malmo/MissionHandlers/BuildBattleDecoratorImplementation.java#L297-L326
public class TempClass {
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        // Disallow creating or destroying events in the player structure:
        if (event instanceof PlayerInteractEvent.LeftClickBlock)
        {
            // Destroy block
            if (blockInBounds(event.getPos(), this.sourceBounds))
                event.setCanceled(true);
        }
        else if (event instanceof PlayerInteractEvent.RightClickBlock)
        {
            // Place block - need to work out *where* the block would be placed.
            // This code was cribbed from ItemBlock.onItemUse()
            IBlockState iblockstate = event.getWorld().getBlockState(event.getPos());
            Block block = iblockstate.getBlock();
            EnumFacing side = event.getFace();
            BlockPos pos = event.getPos();
            if (block == Blocks.SNOW_LAYER && ((Integer)iblockstate.getValue(BlockSnow.LAYERS)).intValue() < 1)
            {
                side = EnumFacing.UP;
            }
            else if (!block.isReplaceable(event.getWorld(), pos))
            {
                pos = pos.offset(side);
            }
            if (blockInBounds(pos, this.sourceBounds))
                event.setCanceled(true);
        }
    }

}