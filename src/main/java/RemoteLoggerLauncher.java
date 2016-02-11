import io.vertx.core.Vertx;

public class RemoteLoggerLauncher {

    public static  void main(String[] args) {
        Vertx.vertx().deployVerticle("com.clue.controller.RemoteLoggerVerticle");
    }
}
