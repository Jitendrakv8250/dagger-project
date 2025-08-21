package framework.transport;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

public class PostRequestHandler implements HttpRequestHandler{
    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public FullHttpResponse handleRequest(FullHttpRequest request) {
       return createResponse(HttpResponseStatus.OK, "POST request received".getBytes());
    }
}
