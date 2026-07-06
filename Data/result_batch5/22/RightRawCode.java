// https://github.com/MyCATApache/Mycat-Server/tree/243539fb74bbdcb9819fecc7e7b50ccf0899e671/src/main/java/io/mycat/net/factory/BackendConnectionFactory.java#L38-L50
public class TempClass {
	protected NetworkChannel openSocketChannel(boolean isAIO)
			throws IOException {
		if (isAIO) {
			return AsynchronousSocketChannel
                .open(MycatServer.getInstance().getNextAsyncChannelGroup());
		} else {
			SocketChannel channel = null;
			channel = SocketChannel.open();
			channel.configureBlocking(false);
			return channel;
		}

	}

}