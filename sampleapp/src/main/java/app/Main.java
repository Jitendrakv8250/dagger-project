package app;

import app.controller.Test1;
import app.controller.Test2;
import framework.transport.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        new Test1().init();
        new Test2().init();
        try {
            server.start(8080);
            System.out.println("Server started on http://localhost:8080");
        } catch (InterruptedException e) {
            System.err.println("Server failed to start: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
