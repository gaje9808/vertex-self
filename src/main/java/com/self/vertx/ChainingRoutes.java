package com.self.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class ChainingRoutes {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        Router router = Router.router(vertx);
        Route handler1 = router
                .route("/helloVertx")
                .handler(routingContext -> {
                    System.out.println("came in hello vertx1");
                    HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.write("Hi from Vertx1");
                    routingContext
                            .vertx()
                            .setTimer(5000, tid -> routingContext.next());
                });

        Route handler2 = router
                .route("/helloVertx")
                .handler(routingContext -> {
                    System.out.println("came in hello vertx2");
                    HttpServerResponse response = routingContext.response();
                    response.write("Hi from Vertx2");
                    routingContext
                            .vertx()
                            .setTimer(5000, tid -> routingContext.next());
                });

        Route handler3 = router
                .route("/helloVertx")
                .handler(routingContext -> {
                    System.out.println("came in hello vertx3");
                    HttpServerResponse response = routingContext.response();
                    response.write("Hi from Vertx1");
                    response.end("Ending greetings");
                });
        httpServer
                .requestHandler(router::accept)
                .listen(8093);

    }
}
