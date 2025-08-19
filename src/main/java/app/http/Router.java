package app.http;

import io.netty.handler.codec.http.HttpMethod;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Router {

    public interface Controller {
        void register(Router router);
    }

    public static class HandlerResult {
        public final int status;
        public final String contentType;
        public final byte[] body;

        public HandlerResult(int status, String contentType, byte[] body) {
            this.status = status;
            this.contentType = contentType;
            this.body = body;
        }

        public static HandlerResult json(byte[] body) {
            return new HandlerResult(200, "application/json; charset=UTF-8", body);
        }
    }

    private final Map<String, BiFunction<HttpRequest, HttpResponse, HandlerResult>> getRoutes = new ConcurrentHashMap<>();
    private final Map<String, BiFunction<HttpRequest, HttpResponse, HandlerResult>> postRoutes = new ConcurrentHashMap<>();

    public void get(String path, BiFunction<HttpRequest, HttpResponse, HandlerResult> handler) {
        getRoutes.put(path, handler);
    }

    public void post(String path, BiFunction<HttpRequest, HttpResponse, HandlerResult> handler) {
        postRoutes.put(path, handler);
    }

    public BiFunction<HttpRequest, HttpResponse, HandlerResult> match(HttpMethod method, String path) {
        switch (method.name()) {
            case "GET": return getRoutes.get(path);
            case "POST": return postRoutes.get(path);
            default: return null;
        }
    }
}
