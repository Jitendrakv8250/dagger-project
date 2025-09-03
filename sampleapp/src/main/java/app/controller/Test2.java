package app.controller;

import app.Book;
import framework.router.Controller;

public class Test2 implements Controller {
    @Override
    public void init() {
       app.GET( "/test2","text/plain",requestContext->new Book("2","Brave New World","Aldous Huxley"));
       // router.POST( "/test2");
    }
}
