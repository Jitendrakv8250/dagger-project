package framework.transport;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

public class UnSupportedRequestHandler implements HttpRequestHandler{
    @Override
    public HttpMethod getMethod() {
        return null;
    }

    @Override
    public FullHttpResponse handleRequest(FullHttpRequest request) {
        return createResponse(HttpResponseStatus.METHOD_NOT_ALLOWED, "Unsupported Http Method".getBytes());
    }
}
