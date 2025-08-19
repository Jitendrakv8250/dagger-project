package app.di;

import app.controllers.HealthController;
import app.controllers.HelloController;
import app.http.Router;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

import javax.inject.Singleton;
import java.util.Set;

@Module
public abstract class ControllerModule {

    @Provides @IntoSet
    static Router.Controller provideHelloController(HelloController c) { return c; }

    @Provides @IntoSet
    static Router.Controller provideHealthController(HealthController c) { return c; }

    @Provides @Singleton
    static Router provideRouter(Set<Router.Controller> controllers) {
        var router = new Router();
        for (var c : controllers) c.register(router);
        return router;
    }
}
