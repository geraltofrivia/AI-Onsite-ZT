// https://github.com/MeteorDevelopment/meteor-client/tree/4da2b5cb1e515d592cf6e723480cf640ddfb5df0/src/main/java/meteordevelopment/meteorclient/systems/modules/misc/swarm/SwarmHost.java#L31-L44
public class TempClass {
    @Override
    public void run() {
        ChatUtils.infoPrefix("Swarm", "Listening for incoming connections on port %s.", socket.getLocalPort());

        while (!isInterrupted()) {
            try {
                Socket connection = socket.accept();
                assignConnectionToSubServer(connection);
            } catch (IOException e) {
                ChatUtils.errorPrefix("Swarm", "Error making a connection to worker.");
                e.printStackTrace();
            }
        }
    }

}