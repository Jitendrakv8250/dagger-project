package app.controllers;

import app.http.HttpRequest;
import app.http.HttpResponse;
import app.http.Router;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class HelloController implements Router.Controller {

    private final ObjectMapper mapper;

    @Inject
    public HelloController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void register(Router router) {
        router.get("/hello", (req, res) -> {
            String name = req.queryParam("name").orElse("Stranger");
            try {
                byte[] body = mapper.writeValueAsBytes(Map.of(
                        "message", "Hello, " + name + "!"
                ));
                return Router.HandlerResult.json(body);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
