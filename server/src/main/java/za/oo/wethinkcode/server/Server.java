package za.oo.wethinkcode.server;

import io.javalin.Javalin;

import za.oo.wethinkcode.api.QuoteApiHandler;

public class Server {
    private final Javalin server;

    public Server() {
        server = Javalin.create(config -> {
            config.defaultContentType = "application/json";
            config.enableCorsForAllOrigins();
        });

        this.server.get("/quotes", context -> QuoteApiHandler.getAll(context)); 
        this.server.get("/quote/{id}", context -> QuoteApiHandler.getOne(context)); 
        this.server.post("/quotes", context -> QuoteApiHandler.create(context)); 
    }

    public void start(int port) {
        this.server.start(port);
    }

    public void stop() {
        this.server.stop();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(5000);
    }
}