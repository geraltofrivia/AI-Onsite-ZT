// https://github.com/jenkinsci/jenkins/tree/235d237f32835ccc1064c95c701f331d7dece7a3/core/src/main/java/hudson/TcpSlaveAgentListener.java#L174-L204
public class TempClass {
    @Override
    public void run() {
        // the loop eventually terminates when the thread shuts down
        while (!shuttingDown) {
            try {
                Socket s = serverSocket.accept().socket();

                // this prevents a connection from silently terminated by the router in between or the other peer
                // and that goes without unnoticed. However, the time out is often very long (for example 2 hours
                // by default in Linux) that this alone is enough to prevent that.
                s.setKeepAlive(true);
                // we take care of buffering on our own
                s.setTcpNoDelay(true);

                new ConnectionHandler(s).start();
            } catch (Throwable e) {
                if (!shuttingDown) {
                    LOGGER.log(Level.SEVERE, "Failed to accept TCP connections", e);
                    if (!serverSocket.isOpen()) {
                        LOGGER.log(Level.INFO, "Restarting server socket");
                        try {
                            serverSocket = createSocket(configuredPort);
                            LOGGER.log(Level.INFO, "TCP agent listener restarted on port {0}", getPort());
                        } catch (IOException ioe) {
                            LOGGER.log(Level.WARNING, "Failed to restart server socket", ioe);
                        }
                    }
                }
            }
        }
    }

}