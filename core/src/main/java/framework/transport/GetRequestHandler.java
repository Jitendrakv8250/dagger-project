package framework.transport;

import framework.router.RequestContext;
import framework.router.ResponseContext;
import framework.router.RouteNode;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import static framework.router.RouteRegistration.ROUTE_REGISTRATION;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

public class GetRequestHandler implements HttpRequestHandler{
    private final ResponseContext responseContext=new ResponseContext();
    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public FullHttpResponse handleRequest(FullHttpRequest request) {

        RouteNode routeNode=ROUTE_REGISTRATION.resolveRoute(getMethod(),request.uri());
        if(routeNode==null){
            return createResponse(HttpResponseStatus.NOT_FOUND,"text/plain", "Route not found".getBytes(CharsetUtil.UTF_8));
        }
        RequestContext requestContext=new RequestContext(routeNode,null,routeNode.getPathVariables());
        var response = requestContext.getRouteNode().getHandler().apply(requestContext);
        if(routeNode.getContentType().equals(HttpHeaderValues.APPLICATION_JSON.toString())){
            String value= responseContext.asJson(response);
            return createResponse(HttpResponseStatus.OK,APPLICATION_JSON,value.getBytes(CharsetUtil.UTF_8));
        }
        return createResponse(HttpResponseStatus.OK,routeNode.getContentType(),response.toString().getBytes(CharsetUtil.UTF_8));
    }


}
