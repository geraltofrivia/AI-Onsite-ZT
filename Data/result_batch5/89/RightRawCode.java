// https://github.com/PaperMC/Velocity/tree/e99407132fcfcf90f03b2554e0ae0bd5f8c83452/proxy/src/main/java/com/velocitypowered/proxy/connection/backend/BungeeCordMessageResponder.java#L183-L194
public class TempClass {
  private void processMessage0(ByteBufDataInput in,
      ComponentSerializer<Component, ?, String> serializer) {
    String target = in.readUTF();
    String message = in.readUTF();

    Component messageComponent = serializer.deserialize(message);
    if (target.equals("ALL")) {
      proxy.sendMessage(messageComponent);
    } else {
      proxy.getPlayer(target).ifPresent(player -> player.sendMessage(messageComponent));
    }
  }

}