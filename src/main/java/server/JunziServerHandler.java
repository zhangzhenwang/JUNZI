package server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JunziServerHandler extends SimpleChannelInboundHandler<String> {




    public void channelReadComplete(ChannelHandlerContext ctx){

        //ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
    }


    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
