package pers.cocoadel.learning.spring.context.lifecycle;

import org.springframework.context.Lifecycle;

public class MyLifecycle implements Lifecycle {
    private boolean running = false;

    @Override
    public void start() {
        running = true;
        System.out.println("MyLifecycle start ...");
    }

    @Override
    public void stop() {
        running = false;
        System.out.println("MyLifecycle stop ...");
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
