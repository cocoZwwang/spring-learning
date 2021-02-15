package pers.cocoadel.learning.spring.context.lifecycle;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class ShutdownHookDemo {

    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> System.out.println(String.format("[the thread: %s] - close context",Thread.currentThread().getName())));
        //
        context.registerShutdownHook();
        context.refresh();
        System.out.println("请输入任意建结束程序");
        System.in.read();
        context.close();
    }
}
