// https://github.com/EssentialsX/Essentials/tree/ff7c95271544c646ae3191ab4ba72f74b7c8f49a/EssentialsProtect/src/main/java/com/earth2me/essentials/protect/EssentialsProtectBlockListener.java#L135-L148
public class TempClass {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        final Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        final World.Environment environment = block.getWorld().getEnvironment();
        if (MaterialUtil.isBed(block.getType()) && !environment.equals(World.Environment.NORMAL)) {
            event.setCancelled(prot.getSettingBool(ProtectConfig.prevent_bed_explosion));
        }
    }

}