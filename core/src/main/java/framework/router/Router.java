package framework.router;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;

import java.util.function.Function;
import java.util.function.Supplier;

import static framework.router.RouteRegistration.ROUTE_REGISTRATION;

public enum Router {
    ROUTE;


    void GET(String uri, String contentType, Function<RequestContext,?> handler){
        addRoute(new RouteNode(HttpMethod.GET,uri,handler, contentType));
    }
    void POST(String uri, String contentType, Function<RequestContext,?> handler){

        addRoute(new RouteNode(HttpMethod.POST,uri,handler, contentType));
    }

    void PUT(String uri){
        addRoute(new RouteNode(HttpMethod.PUT,uri,requestContext -> "Updated resource at-- " + uri, HttpHeaderValues.APPLICATION_JSON.toString()));
    }
    void DELETE(String uri){
        addRoute(new RouteNode(HttpMethod.DELETE,uri, requestContext -> "Deleted resource at-- " + uri, HttpHeaderValues.APPLICATION_JSON.toString()));
    }

    private void addRoute(RouteNode routeNode) {
        ROUTE_REGISTRATION.addRoute(routeNode);
    }
}
