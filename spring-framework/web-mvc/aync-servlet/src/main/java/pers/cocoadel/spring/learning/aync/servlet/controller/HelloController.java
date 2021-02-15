package pers.cocoadel.spring.learning.aync.servlet.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.*;

@RestController
@EnableScheduling
public class HelloController {

    private BlockingQueue<DeferredResult<String>> queue = new LinkedBlockingQueue<>();

    private Random random = new Random();

    @Scheduled(fixedRate = 5000)
    public void process() {
        DeferredResult<String> result = null;
        do {
            try {
                long time = random.nextInt(150);
                result = queue.take();
                Thread.sleep(time);
                result.setResult("hello world!!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (result != null);
    }

    @GetMapping("/aync/hello")
    public DeferredResult<String> hello() {
        long time = System.currentTimeMillis();
        DeferredResult<String> deferredResult = new DeferredResult<>(50L);
        println("hello world!");
        deferredResult.setResultHandler(result -> {
            result = result + "....";
        });
        deferredResult.onCompletion(() -> {
            long costTime = System.currentTimeMillis() - time;
            println("onCompletion!!! " + " cost time : " + costTime);
        });
        deferredResult.onTimeout(() -> {
            long costTime = System.currentTimeMillis() - time;
            println("time out !!!" + " cost time : " + costTime);
        });
        queue.offer(deferredResult);
        return deferredResult;
    }

    @GetMapping("/callable/hello")
    public Callable<String> callableHello() {
        return () -> {
            String result = "hello callable !!!";
            println(result);
            return result;
        };
    }

    @GetMapping("/future/hello")
    public CompletionStage<String> futureHello() {
        return CompletableFuture.supplyAsync(() ->{
            String result = "hello completable future!!!";
            println(result);
            return result;
        });
    }

    @GetMapping("/index")
    public String index() {
        return "hello world!";
    }

    private void println(Object obj) {
        System.out.printf("[Thread: %s] - %s\n", Thread.currentThread().getName(), obj);
    }
}
