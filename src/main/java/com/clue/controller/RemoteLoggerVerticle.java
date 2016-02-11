package com.clue.controller;

import com.clue.service.Service;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class RemoteLoggerVerticle extends AbstractVerticle {
    Service service = new Service();
    RemoteLoggerController controller = new RemoteLoggerController();
    RemoteLoggerControllerWeb controllerWeb = new RemoteLoggerControllerWeb();

    @Override
    public void start() throws Exception {
        service.Initialize();
        HttpServer server = vertx.createHttpServer();
        controller.bind(server, "/remoteLogger");
        controllerWeb.bind(server);
        server.listen(8090);
    }
}