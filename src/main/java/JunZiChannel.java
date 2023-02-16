import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class JunZiChannel {


    public Channel getChannel(String host, Integer port) throws InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        Channel ch =null;
        try{
            final JunziClientHandler junziClientHandler = new JunziClientHandler();
            b
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast(junziClientHandler);
                        }
                    })
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true);
                    //.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);

         ChannelFuture    future=b.connect(host,port).sync();
         ch = future.channel();

        }catch(Exception e){
           e.printStackTrace();
        }finally{
            //group.shutdownGracefully().sync();
        }
        //f.channel().closeFuture().sync();
        return ch;
    }


}
