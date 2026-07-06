// https://github.com/hneemann/Digital/tree/0fc1087e8aef407dffda4846e2b5f1311e9180e2/src/main/java/de/neemann/digital/core/io/telnet/Server.java#L93-L105
public class TempClass {
        @Override
        public void run() {
            try {
                while (true) {
                    Socket client = serverSocket.accept();
                    ClientThread cl = new ClientThread(client, Server.this);
                    cl.start();
                    setClient(cl);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}