package framework.transport;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;
import static framework.router.Router.ROUTE;

public class GetRequestHandler implements HttpRequestHandler{
    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public FullHttpResponse handleRequest(FullHttpRequest request) {
        String response=ROUTE.getHandler(HttpMethod.GET, request.uri()).get();
        return createResponse(HttpResponseStatus.OK, response.getBytes(CharsetUtil.UTF_8));
    }
}
