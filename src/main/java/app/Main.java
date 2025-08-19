package app;

import app.di.DaggerAppComponent;
import app.http.NettyHttpServer;
import javax.inject.Inject;

public class Main {
    private final NettyHttpServer server;

    @Inject
    public Main(NettyHttpServer server) {
        this.server = server;
    }

    public static void main(String[] args) {
        var component = DaggerAppComponent.create();
        var main = component.main();
        main.server().start(8080); // default port
    }

    public NettyHttpServer server() { return server; }
}
