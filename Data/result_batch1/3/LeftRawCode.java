// https://github.com/GeyserMC/Geyser/tree/4746651aeaef5e4d58a58b7bf17125e388d87d7a/core/src/main/java/org/geysermc/geyser/command/defaults/VersionCommand.java#L72-L108
public class TempClass {
    @Override
    public void execute(CommandContext<GeyserCommandSource> context) {
        GeyserCommandSource source = context.sender();

        source.sendMessage(GeyserLocale.getPlayerLocaleString("geyser.commands.version.version", source.locale(),
                GeyserImpl.NAME, GeyserImpl.VERSION, SUPPORTED_JAVA_RANGE, SUPPORTED_BEDROCK_RANGE));

        // Disable update checking in dev mode and for players in Geyser Standalone
        if (!GeyserImpl.getInstance().isProductionEnvironment() || (!source.isConsole() && geyser.getPlatformType() == PlatformType.STANDALONE)) {
            return;
        }

        if (GeyserImpl.IS_DEV) {
            source.sendMessage(GeyserLocale.getPlayerLocaleString("geyser.core.dev_build", source.locale(), "https://discord.gg/geysermc"));
            return;
        }

        source.sendMessage(GeyserLocale.getPlayerLocaleString("geyser.commands.version.checking", source.locale()));
        try {
            int buildNumber = this.geyser.buildNumber();
            JsonNode response = WebUtils.getJson("https://download.geysermc.org/v2/projects/geyser/versions/latest/builds/latest");
            int latestBuildNumber = response.get("build").asInt();

            if (latestBuildNumber == buildNumber) {
                source.sendMessage(GeyserLocale.getPlayerLocaleString("geyser.commands.version.no_updates", source.locale()));
                return;
            }

            source.sendMessage(GeyserLocale.getPlayerLocaleString(
                    "geyser.commands.version.outdated",
                    source.locale(), (latestBuildNumber - buildNumber), "https://geysermc.org/download"
            ));
        } catch (IOException e) {
            GeyserImpl.getInstance().getLogger().error(GeyserLocale.getLocaleStringLog("geyser.commands.version.failed"), e);
            source.sendMessage(ChatColor.RED + GeyserLocale.getPlayerLocaleString("geyser.commands.version.failed", source.locale()));
        }
    }

}