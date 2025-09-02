package framework.transport;

import framework.router.ResponseContext;
import framework.router.RouteNode;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import static framework.router.Router.ROUTE;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

public class GetRequestHandler implements HttpRequestHandler{
    private final ResponseContext responseContext=new ResponseContext();
    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public FullHttpResponse handleRequest(FullHttpRequest request) {
        RouteNode routeNode=ROUTE.getHandler(HttpMethod.GET, request.uri());
        if(routeNode==null){
            return createResponse(HttpResponseStatus.NOT_FOUND,"text/plain", "Route not found".getBytes(CharsetUtil.UTF_8));
        }
        if(routeNode.contentType().equals(HttpHeaderValues.APPLICATION_JSON.toString())){
           String value= responseContext.asJson(routeNode.handler().get());
            return createResponse(HttpResponseStatus.OK,APPLICATION_JSON,value.getBytes(CharsetUtil.UTF_8));
        }
        return createResponse(HttpResponseStatus.OK,routeNode.contentType(),routeNode.handler().get().toString().getBytes(CharsetUtil.UTF_8));
    }
}
