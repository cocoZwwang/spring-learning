package per.cocoadel.learning.spring.annotation.enable.selector;

public class FtpServer implements Server{
    @Override
    public void start() {
        System.out.println("start Ftp Server");
    }
}
