package framework.transport;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;


public class HttpInboundHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final RequestResolver requestResolver;

    public HttpInboundHandler() {
        this.requestResolver=new RequestResolver();
    }
    @Override
    public void channelReadComplete(io.netty.channel.ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    @Override
    public void exceptionCaught(io.netty.channel.ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        FullHttpResponse response=this.requestResolver
                .resolve(msg.method())
                .handleRequest(msg);
             ctx.writeAndFlush(response).addListener(future -> {
            if (!future.isSuccess()) {
                System.err.println("Failed to send response: " + future.cause());
            }
        });
    }
}
