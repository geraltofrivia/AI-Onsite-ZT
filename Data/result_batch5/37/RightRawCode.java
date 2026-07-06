// https://github.com/AsamK/signal-cli/tree/f9a36c6e0404d06bd396b24b5ea699e49ed29b89/src/main/java/org/asamk/signal/util/IOUtils.java#L137-L151
public class TempClass {
    public static ServerSocketChannel bindSocket(final SocketAddress address) throws IOErrorException {
        final ServerSocketChannel serverChannel;
        try {
            preBind(address);
            serverChannel = address instanceof UnixDomainSocketAddress
                    ? ServerSocketChannel.open(StandardProtocolFamily.UNIX)
                    : ServerSocketChannel.open();
            serverChannel.bind(address);
            logger.debug("Listening on socket: " + address);
            postBind(address);
        } catch (IOException e) {
            throw new IOErrorException("Failed to bind socket " + address + ": " + e.getMessage(), e);
        }
        return serverChannel;
    }

}