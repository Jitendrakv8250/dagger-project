package framework.transport;

import io.netty.handler.codec.http.HttpMethod;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestResolver {
   private Map<HttpMethod, HttpReuqestHandler> requestHandlers;

   public RequestResolver() {
       HttpReuqestHandler getRequestHandler=new GetRequestHandler();
       HttpReuqestHandler postRequestHandler=new PostRequestHandler();
       List<HttpReuqestHandler> requestHandlers=List.of(getRequestHandler, postRequestHandler);
       createRequestHandler(requestHandlers);
   }

   private void createRequestHandler(List<HttpReuqestHandler> handlers) {
       this.requestHandlers = handlers.stream()
               .collect(Collectors.toMap(HttpReuqestHandler::getMethod, handler -> handler));
   }

    public HttpReuqestHandler resolve(HttpMethod method) {
         return requestHandlers.get(method);
    }


}
