package framework;


import framework.transport.Server;

public class Starter {

    public static void main(String[] args) throws InterruptedException {
        Server server=new Server();
        server.start(8080);
    }
}
