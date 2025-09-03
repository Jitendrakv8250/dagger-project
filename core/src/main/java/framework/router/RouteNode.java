package framework.router;

import io.netty.handler.codec.http.HttpMethod;

import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

public class RouteNode{
    private HttpMethod method;
    private String path;
    private Function<RequestContext,?> handler;
    private String contentType;
    private Pattern pattern;

    public Map<String, String> getPathVariables() {
        return pathVariables;
    }

    public void setPathVariables(Map<String, String> pathVariables) {
        this.pathVariables = pathVariables;
    }

    private Map<String, String> pathVariables;

    public RouteNode(HttpMethod method, String path, Function<RequestContext,?> handler, String contentType) {
        this.method = method;
        this.path = path;
        this.handler = handler;
        this.contentType = contentType;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Function<RequestContext,?> getHandler() {
        return handler;
    }

    public void setHandler(Function<RequestContext,?> handler) {
        this.handler = handler;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

}
