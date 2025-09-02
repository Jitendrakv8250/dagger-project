package framework.router;

import io.netty.handler.codec.http.HttpMethod;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public enum Router {

    ROUTE();

    public void GET(String uri){
        addRoute(HttpMethod.GET,uri,() -> "Fetched resource at ---" + uri);
    }
    public void POST(String uri){
        addRoute(HttpMethod.POST,uri,() -> "Created resource at --" + uri);
    }

    /*public void PUT(String uri){
        addRoute(HttpMethod.PUT,uri,() -> "Updated resource at-- " + uri);
    }
    public void DELETE(String uri){
        addRoute(HttpMethod.DELETE,uri, () -> "Deleted resource at-- " + uri);
    }*/

    private final Map<HttpMethod,Map<String, Supplier<String>>> routes = new HashMap<>() {{
        put(HttpMethod.GET, new HashMap<>());
        put(HttpMethod.POST, new HashMap<>());
        put(HttpMethod.PUT, new HashMap<>());
        put(HttpMethod.DELETE, new HashMap<>());
    }};
    public void addRoute(HttpMethod httpMethod,String path, Supplier<String> handler) {
        routes.get(httpMethod).put(path, handler);
    }

    public Supplier<String> getHandler(HttpMethod httpMethod,String path) {
        return routes.get(httpMethod).get(path);
    }
}
