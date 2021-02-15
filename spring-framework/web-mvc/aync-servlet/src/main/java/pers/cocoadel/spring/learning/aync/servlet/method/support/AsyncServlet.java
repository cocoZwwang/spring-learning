package pers.cocoadel.spring.learning.aync.servlet.method.support;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


@WebServlet(name = "asyncServlet",asyncSupported = true,urlPatterns = "/async-servlet")
public class AsyncServlet extends HttpServlet {

    private final Random random = new Random();

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(500L);
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                println("asyncServlet onComplete");
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                println("asyncServlet onTimeout");
            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {
                println("asyncServlet onError");
            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                println("asyncServlet onStartAsync");
            }
        });


        ServletResponse response = asyncContext.getResponse();
        PrintWriter writer = response.getWriter();
        writer.write(String.format("hello world, I am asyncServlet[%s]!!!", AsyncServlet.this.toString()));
        writer.flush();

        long time = random.nextInt(1000);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        asyncContext.complete();
    }

    private void println(Object obj) {
        System.out.printf("[Thread: %s] - %s\n", Thread.currentThread().getName(), obj);
    }
}
