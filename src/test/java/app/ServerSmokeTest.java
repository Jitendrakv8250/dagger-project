package app;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ServerSmokeTest {

    @Test
    void hello_and_health_endpoints_work() throws Exception {
        // Start server in background thread on a random port (8081)
        var exec = Executors.newSingleThreadExecutor();
        exec.submit(() -> {
            Main.main(new String[0]); // starts on 8080 (see Main)
        });
        // Wait a bit for server to start
        TimeUnit.SECONDS.sleep(1);

        HttpClient client = HttpClient.newHttpClient();

        // Health
        var healthResp = client.send(
            HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/health")).GET().build(),
            HttpResponse.BodyHandlers.ofString()
        );
        assertEquals(200, healthResp.statusCode());
        assertEquals("OK", healthResp.body());

        // Hello
        var helloResp = client.send(
            HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/hello?name=World")).GET().build(),
            HttpResponse.BodyHandlers.ofString()
        );
        assertEquals(200, helloResp.statusCode());
        assertTrue(helloResp.body().contains("Hello, World!"));

        exec.shutdownNow();
    }
}
