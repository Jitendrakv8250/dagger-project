package app.http;

import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.FullHttpRequest;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HttpRequest {
    private final FullHttpRequest req;
    private final QueryStringDecoder query;

    public HttpRequest(FullHttpRequest req) {
        this.req = req;
        this.query = new QueryStringDecoder(req.uri());
    }

    public String path() {
        return query.path();
    }

    public String method() {
        return req.method().name();
    }

    public Optional<String> queryParam(String name) {
        Map<String, List<String>> params = query.parameters();
        var values = params.get(name);
        if (values == null || values.isEmpty()) return Optional.empty();
        return Optional.of(values.getFirst());
    }

    public String bodyAsString() {
        return req.content().toString(StandardCharsets.UTF_8);
    }

    public FullHttpRequest raw() {
        return req;
    }
}
