package app.controller;

import app.Book;
import framework.router.Controller;

public class Test1 implements Controller {
    @Override
    public void init() {
        router.GET( "/test1","text/plain",()->"Hello from Test1 GET");
        router.GET("/test1Object","application/json",()->new Book("1","1984","George Orwell"));
        router.POST("/test1","application/json",()->"success");
    }
}
