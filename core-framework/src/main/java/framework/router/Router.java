package framework.router;

import io.netty.handler.codec.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {

    private Map<HttpMethod, List<Route>> routes;

    public Router() {
        this.routes = new HashMap<>();
    }
}
