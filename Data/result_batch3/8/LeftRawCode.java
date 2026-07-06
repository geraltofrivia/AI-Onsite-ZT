// https://github.com/648540858/wvp-GB28181-pro/tree/ed2f104529053a7215e138581fe8d8dd2eb665e9/src/main/java/com/genersoft/iot/vmp/jt1078/codec/netty/TcpServer.java#L44-L82
public class TempClass {
    private void startTcpServer() {
        try {
            CodecFactory.init();
            this.bossGroup = new NioEventLoopGroup();
            this.workerGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.group(bossGroup, workerGroup);

            bootstrap.option(NioChannelOption.SO_BACKLOG, 1024)
                    .option(NioChannelOption.SO_REUSEADDR, true)
                    .childOption(NioChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        public void initChannel(NioSocketChannel channel) {
                            channel.pipeline()
                                    .addLast(new IdleStateHandler(10, 0, 0, TimeUnit.MINUTES))
                                    .addLast(new DelimiterBasedFrameDecoder(1024 * 2, DECODER_JT808))
                                    .addLast(new Jt808Decoder())
                                    .addLast(new Jt808Encoder())
                                    .addLast(new Jt808EncoderCmd())
                                    .addLast(new Jt808Handler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            // 监听设备TCP端口是否启动成功
            channelFuture.addListener(future -> {
                if (!future.isSuccess()) {
                    log.error("Binding port:{} fail!  cause: {}", port, future.cause().getCause(), future.cause());
                }
            });
            log.info("服务:JT808 Server 启动成功, port:{}", port);
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.warn("服务:JT808 Server 启动异常, port:{},{}", port, e.getMessage(), e);
        } finally {
            stop();
        }
    }

}