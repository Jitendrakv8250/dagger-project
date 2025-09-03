package app.controller;
import app.Book;
import framework.router.Controller;

public class Test1 implements Controller {
    @Override
    public void init() {
        app.GET( "/test1/{id}","text/plain",requestContext->"Hello from Test1 GET---"+requestContext.getPathVariable().get("id"));
        app.GET("/test1Object/{sid}","application/json",requestContext->new Book("1","1984","George Orwell")+requestContext.getPathVariable().get("sid"));
        app.GET("/test1/{id}/order/{sid}/{you}/{kid}","application/json",requestContext->"success"+requestContext.getPathVariable());
    }
}
