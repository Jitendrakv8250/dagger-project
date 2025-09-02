package app.controller;

import framework.router.Controller;
import framework.router.Router;

public class Test1 implements Controller {
    @Override
    public void init(Router router) {
        router.GET( "/test1",);
        router.POST( "/test1");
        //router.GET( "/test1");
       // router.GET( "/test1");
    }
}
