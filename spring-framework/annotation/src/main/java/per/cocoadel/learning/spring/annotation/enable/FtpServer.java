package per.cocoadel.learning.spring.annotation.enable;

public class FtpServer implements Server{
    @Override
    public void start() {
        System.out.println("start Ftp Server");
    }
}
