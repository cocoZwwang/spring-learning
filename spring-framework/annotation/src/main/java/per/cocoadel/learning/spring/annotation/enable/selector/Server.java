package per.cocoadel.learning.spring.annotation.enable.selector;

public interface Server {
    void start();

    enum Type {
        Http, Ftp;
    }
}
