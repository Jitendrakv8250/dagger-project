package app.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Lazy;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Singleton
public class NettyHttpServer {
    private final EventLoopGroup group;
    private final Router router;
    private final ObjectMapper mapper;

    @Inject
    public NettyHttpServer(EventLoopGroup group, Router router, ObjectMapper mapper) {
        this.group = group;
        this.router = router;
        this.mapper = mapper;
    }

    public void start(int port) {
        ServerBootstrap b = new ServerBootstrap();
        b.group(group)
         .channel(NioServerSocketChannel.class)
         .handler(new LoggingHandler(LogLevel.INFO))
         .childHandler(new ChannelInitializer<SocketChannel>() {
             @Override
             protected void initChannel(SocketChannel ch) {
                 ChannelPipeline p = ch.pipeline();
                 p.addLast(new HttpServerCodec());
                 p.addLast(new HttpObjectAggregator(1024 * 1024));
                 p.addLast(new SimpleChannelInboundHandler<FullHttpRequest>() {
                     @Override
                     protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) {
                         handle(ctx, req);
                     }

                     @Override
                     public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                         cause.printStackTrace();
                         ctx.close();
                     }
                 });
             }
         });

        try {
            Channel ch = b.bind(port).sync().channel();
            System.out.println("Server started on http://localhost:" + port);
            ch.closeFuture().sync(); // block
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            group.shutdownGracefully();
        }
    }

    private void handle(ChannelHandlerContext ctx, FullHttpRequest req) {
        HttpRequest request = new HttpRequest(req);
        HttpResponse response = new HttpResponse();

        var handler = router.match(req.method(), request.path());
        Router.HandlerResult result;
        if (handler != null) {
            try {
                result = handler.apply(request, response);
                if (result == null) throw new IllegalStateException("Handler returned null");
            } catch (Exception e) {
                byte[] error = ("{\"error\":\"" + e.getMessage() + "\"}").getBytes(StandardCharsets.UTF_8);
                result = new Router.HandlerResult(500, "application/json; charset=UTF-8", error);
            }
        } else {
            byte[] notFound = "{\"error\":\"Not Found\"}".getBytes(StandardCharsets.UTF_8);
            result = new Router.HandlerResult(404, "application/json; charset=UTF-8", notFound);
        }

        FullHttpResponse nettyResp = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.valueOf(result.status),
                Unpooled.wrappedBuffer(result.body)
        );
        nettyResp.headers().set(HttpHeaderNames.CONTENT_TYPE, result.contentType);
        nettyResp.headers().set(HttpHeaderNames.CONTENT_LENGTH, result.body.length);

        ctx.writeAndFlush(nettyResp).addListener(ChannelFutureListener.CLOSE);
    }
}
