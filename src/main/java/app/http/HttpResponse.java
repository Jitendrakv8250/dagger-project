package app.http;

import io.netty.handler.codec.http.FullHttpResponse;

public class HttpResponse {
    private FullHttpResponse raw;

    public void setRaw(FullHttpResponse raw) {
        this.raw = raw;
    }
    public FullHttpResponse raw() { return raw; }
}
