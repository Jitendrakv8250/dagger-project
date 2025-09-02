package app.controller;

import framework.router.Controller;
import framework.router.Router;

public class Test2 implements Controller {
    @Override
    public void init(Router router) {
        router.GET( "/test2");
        router.POST( "/test2");
    }
}
