package app.di;

import app.Main;
import app.http.NettyHttpServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
        JacksonModule.class,
        NettyModule.class,
        ControllerModule.class
})
public interface AppComponent {
    Main main();
    NettyHttpServer server();
    ObjectMapper objectMapper();
}
