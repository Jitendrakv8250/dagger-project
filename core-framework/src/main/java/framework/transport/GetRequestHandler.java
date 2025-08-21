package framework.transport;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

public class GetRequestHandler implements HttpReuqestHandler{
    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public FullHttpResponse handleRequest(FullHttpRequest request) {
        return createResponse(HttpResponseStatus.OK, "GET request received".getBytes(CharsetUtil.UTF_8));
    }
}
