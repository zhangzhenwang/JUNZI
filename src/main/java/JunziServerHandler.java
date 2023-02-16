
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JunziServerHandler extends SimpleChannelInboundHandler<String> {


//    public void channelRead(ChannelHandlerContext ctx,String msg){
//
//        System.out.println(msg);
//
//    }

    public void channelReadComplete(ChannelHandlerContext ctx){

        //ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
    }


    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
