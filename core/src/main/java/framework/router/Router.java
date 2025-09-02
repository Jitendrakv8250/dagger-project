package framework.router;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
public enum Router {

    ROUTE();

    public void GET(String uri, String contentType, Supplier<?> handler){
        addRoute(new RouteNode(HttpMethod.GET,uri,handler, contentType));
    }
    public void POST(String uri,String contentType,Supplier<?> handler){

        addRoute(new RouteNode(HttpMethod.POST,uri,handler, contentType));
    }

    /*public void PUT(String uri){
        addRoute(HttpMethod.PUT,uri,() -> "Updated resource at-- " + uri);
    }
    public void DELETE(String uri){
        addRoute(HttpMethod.DELETE,uri, () -> "Deleted resource at-- " + uri);
    }*/

    private final Map<HttpMethod,Map<String, RouteNode>> routes = new HashMap<>() {{
        put(HttpMethod.GET, new HashMap<>());
        put(HttpMethod.POST, new HashMap<>());
        put(HttpMethod.PUT, new HashMap<>());
        put(HttpMethod.DELETE, new HashMap<>());
    }};
    public void addRoute(RouteNode routeNode) {
        routes.get(routeNode.method()).put(routeNode.path(), routeNode);
    }

    public RouteNode getHandler(HttpMethod httpMethod,String path) {
        return routes.get(httpMethod).get(path);
    }
}
