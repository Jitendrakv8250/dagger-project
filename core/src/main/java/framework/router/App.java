package framework.router;

import java.util.function.Function;
import java.util.function.Supplier;
import static framework.router.Router.ROUTE;

public enum App {
    APP;
    public void GET(String uri, String contentType, Function<RequestContext,?> handler ){
        ROUTE.GET(uri, contentType, handler);
    }
    public void POST(String uri,String contentType,Function<RequestContext,?> handler){
        ROUTE.POST(uri, contentType, handler);
    }

    public void PUT(String uri){
        ROUTE.PUT(uri);
    }
    public void DELETE(String uri){
        ROUTE.DELETE(uri);
    }

}
