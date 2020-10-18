package per.cocoadel.learning.spring.annotation.enable;

public class HttpServer implements Server{
    @Override
    public void start() {
        System.out.println("start http server");
    }
}
