// https://github.com/GeyserMC/Geyser/tree/4746651aeaef5e4d58a58b7bf17125e388d87d7a/core/src/main/java/org/geysermc/geyser/network/UpstreamPacketHandler.java#L232-L272
public class TempClass {
    @Override
    public PacketSignal handle(ResourcePackClientResponsePacket packet) {
        switch (packet.getStatus()) {
            case COMPLETED -> {
                if (geyser.getConfig().getRemote().authType() != AuthType.ONLINE) {
                    session.authenticate(session.getAuthData().name());
                } else if (!couldLoginUserByName(session.getAuthData().name())) {
                    // We must spawn the white world
                    session.connect();
                }
                geyser.getLogger().info(GeyserLocale.getLocaleStringLog("geyser.network.connect", session.getAuthData().name()));
            }
            case SEND_PACKS -> {
                packsToSend.addAll(packet.getPackIds());
                sendPackDataInfo(packsToSend.pop());
            }
            case HAVE_ALL_PACKS -> {
                ResourcePackStackPacket stackPacket = new ResourcePackStackPacket();
                stackPacket.setExperimentsPreviouslyToggled(false);
                stackPacket.setForcedToAccept(false); // Leaving this as false allows the player to choose to download or not
                stackPacket.setGameVersion(session.getClientData().getGameVersion());
                stackPacket.getResourcePacks().addAll(this.resourcePackLoadEvent.orderedPacks());
                // Allows Vibrant Visuals to be toggled in the settings
                if (session.isAllowVibrantVisuals() && !GameProtocol.is1_21_90orHigher(session)) {
                    stackPacket.getExperiments().add(new ExperimentData("experimental_graphics", true));
                }

                if (GameProtocol.is1_21_80(session)) {
                    // Support happy ghasts in .80
                    stackPacket.getExperiments().add(new ExperimentData("y_2025_drop_2", true));
                    // Enables the locator bar for 1.21.80 clients
                    stackPacket.getExperiments().add(new ExperimentData("locator_bar", true));
                }

                session.sendUpstreamPacket(stackPacket);
            }
            default -> session.disconnect("disconnectionScreen.resourcePack");
        }

        return PacketSignal.HANDLED;
    }

}