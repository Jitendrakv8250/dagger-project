package framework.transport;

import framework.router.RouteNode;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

import static framework.router.RouteRegistration.ROUTE_REGISTRATION;

public class PostRequestHandler implements HttpRequestHandler{

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public FullHttpResponse handleRequest(FullHttpRequest request) {
        RouteNode routeNode=ROUTE_REGISTRATION.resolveRoute(getMethod(),request.uri());
        if(routeNode==null){
            return createResponse(HttpResponseStatus.NOT_FOUND,"text/plain", "Route not found".getBytes());
        }
        String response="response-->"+request.uri();
        return createResponse(HttpResponseStatus.OK,"text/plain" ,response.getBytes());
    }
}
