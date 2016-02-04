package com.clue;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Server extends AbstractVerticle {
    LogManager lynx = new LogManager();

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        lynx.bind(server, "/vl");
        server.listen(8080);
    }
}