package per.cocoadel.learning.spring.annotation.enable;

public interface Server {
    void start();

    enum Type {
        Http, Ftp;
    }
}
