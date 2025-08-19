package app.di;

import dagger.Module;
import dagger.Provides;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import javax.inject.Singleton;

@Module
public class NettyModule {
    @Provides @Singleton
    EventLoopGroup provideEventLoopGroup() {
        // Boss/worker split can be added later; simple single group for small apps
        return new NioEventLoopGroup();
    }
}
