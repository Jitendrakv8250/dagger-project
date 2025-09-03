package framework.router;

import java.util.Map;

public class RequestContext {
    private RouteNode routeNode;
    private Object requestBody;
    private Map<String,String> pathVariables;
    public RequestContext(RouteNode routeNode, Object requestBody, Map<String, String> pathVariables) {
        this.routeNode = routeNode;
        this.requestBody = requestBody;
        this.pathVariables = pathVariables;
    }
    public RouteNode getRouteNode() {
        return routeNode;
    }
    public Map<String,String> getPathVariable() {
        return pathVariables;
    }
}
