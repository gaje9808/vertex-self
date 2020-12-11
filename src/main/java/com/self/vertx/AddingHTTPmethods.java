package com.self.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class AddingHTTPmethods {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        Router router = Router.router(vertx);
        Route handler1 = router
                .get("/hello")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.write("Getting Hello");

                    response.end("Getting Ended");
                });

        Route handler2 = router
                .post("/hello")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.write("posting Hello");
                    response.end("Posting Ended");
                });

        httpServer
                .requestHandler(router::accept)
                .listen(8094);



    }
}
