package framework.transport;

import io.netty.handler.codec.http.HttpMethod;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestResolver {

    private Map<HttpMethod, HttpRequestHandler> requestHandlers;

   public RequestResolver() {
       HttpRequestHandler getRequestHandler=new GetRequestHandler();
       HttpRequestHandler postRequestHandler=new PostRequestHandler();
       List<HttpRequestHandler> requestHandlers=List.of(getRequestHandler, postRequestHandler);
       createRequestHandler(requestHandlers);
   }

   private void createRequestHandler(List<HttpRequestHandler> handlers) {
       this.requestHandlers = handlers.stream()
               .collect(Collectors.toMap(HttpRequestHandler::getMethod, handler -> handler));
   }

    public HttpRequestHandler resolve(HttpMethod method) {
         return requestHandlers.getOrDefault(method,
                new UnSupportedRequestHandler());
    }


}
