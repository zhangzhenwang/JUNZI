import io.netty.channel.Channel;

public class JunziClient {

     public  static void main(String[] args) throws InterruptedException {

//         NioEventLoopGroup group = new NioEventLoopGroup();
//         try {
//             Bootstrap bootstrap = new Bootstrap();
//             bootstrap
//                     .group(group)
//                     .channel(NioSocketChannel.class)
//                     .handler(new ChannelInitializer<SocketChannel>() {
//                         @Override
//                         protected void initChannel(SocketChannel socketChannel) throws Exception {
//                             ChannelPipeline pipeline = socketChannel.pipeline();
//                             pipeline.addLast("decoder", new StringDecoder());
//                             pipeline.addLast("encoder", new StringEncoder());
//                             pipeline.addLast(new JunziClientHandler());
//                         }
//                     });
//             ChannelFuture future = bootstrap.connect("127.0.0.1", 9000).sync();
//             Channel channel = future.channel();
//            channel.writeAndFlush("1");
//         } finally {
//             group.shutdownGracefully();
//         }
//     }
        JunZiChannel junZiChannel = new JunZiChannel();
        Channel ch = junZiChannel.getChannel("127.0.0.1",9000);
        ch.writeAndFlush("123456789").sync();
         ch.writeAndFlush("123456789").sync();
         ch.writeAndFlush("123456789").sync();
         ch.writeAndFlush("123456789").sync();
         ch.writeAndFlush("123456789").sync();
         ch.writeAndFlush("123456789").sync();

     }
}
