// https://github.com/frohoff/ysoserial/tree/b7d0f27b46af06bbced7dbafddc49678179d3708/src/main/java/ysoserial/exploit/JenkinsCLI.java#L103-L124
public class TempClass {
    public static Channel openChannel ( InetSocketAddress isa ) throws IOException, SocketException {
        System.err.println("* Opening socket " + isa);
        Socket s = SocketFactory.getDefault().createSocket(isa.getAddress(), isa.getPort());
        s.setKeepAlive(true);
        s.setTcpNoDelay(true);
    
        System.err.println("* Opening channel");
        OutputStream outputStream = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(outputStream);
        dos.writeUTF("Protocol:CLI-connect");
        ExecutorService cp = Executors.newCachedThreadPool(new ThreadFactory() {
    
            public Thread newThread ( Runnable r ) {
                Thread t = new Thread(r, "Channel");
                t.setDaemon(true);
                return t;
            }
        });
        Channel c = new ChannelBuilder("EXPLOIT", cp).withMode(Mode.BINARY).build(s.getInputStream(), outputStream);
        System.err.println("* Channel open");
        return c;
    }

}