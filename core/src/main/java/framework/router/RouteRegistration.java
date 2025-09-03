package framework.router;

import io.netty.handler.codec.http.HttpMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RouteRegistration {
    ROUTE_REGISTRATION;
    private final Map<HttpMethod, List<RouteNode>> routes = new HashMap<>() {{
        put(HttpMethod.GET, new ArrayList<>());
        put(HttpMethod.POST, new ArrayList<>());
        put(HttpMethod.PUT, new ArrayList<>());
        put(HttpMethod.DELETE, new ArrayList<>());
    }};

    public void addRoute(RouteNode routeNode) {
        String path = routeNode.getPath();
        String regex = path.replaceAll("\\{\\w+}", "([^/]+)");
        System.out.println(regex);
        Pattern pattern = Pattern.compile("^" + regex + "$");
        routeNode.setPattern(pattern);
        routes.get(routeNode.getMethod()).add(routeNode);
    }

    public RouteNode resolveRoute(HttpMethod method, String uri) {
        List<RouteNode> routeNodes=routes.get(method);
        for(RouteNode e:routeNodes) {
            RouteNode routeNode=extractPathVariables(e,uri);
            if(routeNode!=null){
                return routeNode;
            }
        }
        return null;
    }


    public static RouteNode extractPathVariables(RouteNode routeNode, String uri) {
        Pattern pattern = routeNode.getPattern();
        Matcher matcher = pattern.matcher(uri);
        Map<String, String> pathVars = new HashMap<>();
        if (matcher.matches()) {
            String[] templateParts = routeNode.getPath().split("/");
            int groupIdx = 1;
            for (String part : templateParts) {
                if (part.startsWith("{") && part.endsWith("}")) {
                    String varName = part.substring(1, part.length() - 1);
                    pathVars.put(varName, matcher.group(groupIdx++));
                }
            }
            routeNode.setPathVariables(pathVars);
            return routeNode;
        }
        return null;
    }

}
