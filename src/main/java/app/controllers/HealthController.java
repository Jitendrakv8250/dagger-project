package app.controllers;

import app.http.HttpRequest;
import app.http.HttpResponse;
import app.http.Router;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.charset.StandardCharsets;

@Singleton
public class HealthController implements Router.Controller {
    @Inject
    public HealthController() {}

    @Override
    public void register(Router router) {
        router.get("/health", (req, res) ->
            new Router.HandlerResult(
                200,
                "text/plain; charset=UTF-8",
                "OK".getBytes(StandardCharsets.UTF_8)
            )
        );
    }
}
