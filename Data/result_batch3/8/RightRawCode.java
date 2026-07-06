// https://github.com/qunarcorp/bistoury/tree/b83b87032c3a394df31300a4fe3a1123cf6d7917/bistoury-proxy/src/main/java/qunar/tc/bistoury/proxy/communicate/agent/NettyServerForAgent.java#L74-L103
public class TempClass {
    @Override
    public void start() {
        ConnectionCounterHandler connectionCounterHandler = new ConnectionCounterHandler("agent");
        ServerBootstrap bootstrap = new ServerBootstrap()
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, DEFAULT_WRITE_LOW_WATER_MARK)
                .option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, DEFAULT_WRITE_HIGH_WATER_MARK)
                .group(BOSS_GROUP, WORKER_GROUP)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("connectionCounter", connectionCounterHandler)
                                .addLast("encoder", new AgentEncoder())
                                .addLast("decoder", new AgentDecoder())
                                .addLast("idleHandler", new IdleStateHandler(heartbeatTimeoutSec, 0, 0, TimeUnit.SECONDS))
                                .addLast("messageHandler", agentMessageHandler)
                                .addLast("closeHandler", new ChannelCloseHandler("agent"));
                    }
                });

        try {
            this.channel = bootstrap.bind(port).sync().channel();
            logger.info("netty server for agent, port {}", port);
        } catch (Exception e) {
            logger.error("netty server for agent start fail", e);
            throw Throwables.propagate(e);
        }
    }

}