package framework.transport;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import javax.xml.stream.events.Characters;
import java.nio.ByteBuffer;


public class HttpInboundHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


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
           String uri=msg.uri();
           HttpMethod method=msg.method();
        var content= "server is up & running !".getBytes(CharsetUtil.UTF_8);
        int contentLength=content.length;
        ByteBuf buf= Unpooled.copiedBuffer(content);

        FullHttpResponse response;
        if(uri.equals("/health") && method.equals(HttpMethod.GET)) {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK, buf);
               response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
               response.headers().set(HttpHeaderNames.CONTENT_LENGTH,contentLength);
        } else {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,0);
        }
        ctx.writeAndFlush(response);
    }
}
