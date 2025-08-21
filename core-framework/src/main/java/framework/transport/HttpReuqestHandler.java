package framework.transport;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;

public interface HttpReuqestHandler {

    HttpMethod getMethod();
    FullHttpResponse handleRequest(FullHttpRequest request);

    default FullHttpResponse createResponse(HttpResponseStatus status, byte[] content) {
            int contentLength=content.length;
            ByteBuf buf= Unpooled.copiedBuffer(content);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK, buf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,contentLength);
            return response;
    }
}
