import io.vertx.core.Vertx;

public class LogLauncher {

    public static  void main(String[] args) {
        Vertx.vertx().deployVerticle("com.clue.Server");
    }
}
