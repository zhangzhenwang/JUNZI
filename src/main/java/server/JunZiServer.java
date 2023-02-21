package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JunZiServer implements Server{

    protected static final Logger logger = LoggerFactory.getLogger(JunZiServer.class);

    private Thread thread;


    public void start(final int port) {

        thread = new Thread(new Runnable() {
            public void run() {
                NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
                NioEventLoopGroup workerGroup = new NioEventLoopGroup(8);
                try {
                    ServerBootstrap serverBootstrap = new ServerBootstrap();
                    serverBootstrap
                            .group(boosGroup, workerGroup)
                            .channel(NioServerSocketChannel.class)
                            .option(ChannelOption.SO_BACKLOG, 1024)
                            .childHandler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel socketChannel) throws Exception {
                                    ChannelPipeline pipeline = socketChannel.pipeline();
                                    pipeline.addLast("decoder", new StringDecoder());
                                    pipeline.addLast("encoder", new StringEncoder());
                                    pipeline.addLast(new JunziServerHandler());
                                }
                            });
                    ChannelFuture future = serverBootstrap.bind(port).sync();
                    logger.info("JunZi.Server.start");
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    boosGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();


    }

    public void stop() {

        if(thread!=null&&thread.isAlive()){
            thread.interrupt();
        }
        logger.info("JunZi.Server.Stop");

    }
}
