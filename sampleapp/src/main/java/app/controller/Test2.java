package app.controller;

import app.Book;
import framework.router.Controller;
import framework.router.Router;

public class Test2 implements Controller {
    @Override
    public void init() {
       router.GET( "/test2","text/plain",()->new Book("2","Brave New World","Aldous Huxley"));
       // router.POST( "/test2");
    }
}
