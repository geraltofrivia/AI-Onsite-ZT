// https://github.com/qunarcorp/bistoury/tree/b83b87032c3a394df31300a4fe3a1123cf6d7917/bistoury-proxy/src/main/java/qunar/tc/bistoury/proxy/communicate/ui/handler/UiRequestHandler.java#L72-L97
public class TempClass {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (!(msg instanceof Datagram)) {
            super.write(ctx, msg, promise);
            return;
        }

        Datagram datagram = (Datagram) msg;

        String id = Strings.nullToEmpty(datagram.getHeader().getId());
        Session session = sessionManager.getSession(id);
        if (session == null) {
            super.write(ctx, msg, promise);
            return;
        }

        Optional<CommunicateCommand> communicateCommand = commandStore.getCommunicateCommand(session.getRequestData().getType());
        if (!communicateCommand.isPresent()) {
            logger.warn("unknown command response, {}", datagram);
            session.finish();
            return;
        }

        Datagram response = communicateCommand.get().getProcessor().prepareResponse(datagram);
        super.write(ctx, response, promise);
    }

}