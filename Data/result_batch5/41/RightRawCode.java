// https://github.com/Pugmatt/BedrockConnect/tree/aa5186eb3a7ac8195a073f935ebbf2481281476d/serverlist-server/src/main/com/pyratron/pugmatt/bedrockconnect/server/PacketHandler.java#L495-L515
public class TempClass {
    @Override
    public PacketSignal handle(ResourcePackClientResponsePacket packet) {
        switch (packet.getStatus()) {
            case COMPLETED:
                BedrockConnect.getDataUtil().initializePlayerData(uuid, name, session, this);
                break;
            case HAVE_ALL_PACKS:
                ResourcePackStackPacket rs = new ResourcePackStackPacket();
                //rs.setExperimental(false);
                rs.setForcedToAccept(false);
                rs.setGameVersion("*");
                rs.setForcedToAccept(false);
                session.sendPacket(rs);
                break;
            default:
                session.disconnect("disconnectionScreen.resourcePack");
                break;
        }

        return PacketSignal.HANDLED;
    }

}