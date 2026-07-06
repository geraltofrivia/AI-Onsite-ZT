// https://github.com/JabRef/jabref/tree/6acae662af7a64d25c15dd23dc2f2b5e195be7ec/jablib/src/main/java/org/jabref/logic/remote/server/RemoteListenerServer.java#L32-L51
public class TempClass {
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                try (Socket socket = serverSocket.accept()) {
                    socket.setSoTimeout(TIMEOUT);
                    try (Protocol protocol = new Protocol(socket)) {
                        Pair<RemoteMessage, Object> input = protocol.receiveMessage();
                        handleMessage(protocol, input.getKey(), input.getValue());
                    }
                } catch (SocketException ex) {
                    return;
                } catch (IOException e) {
                    LOGGER.warn("RemoteListenerServer crashed", e);
                }
            }
        } finally {
            closeServerSocket();
        }
    }

}