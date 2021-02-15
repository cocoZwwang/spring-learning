package pers.cocoadel.spring.learning.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RestController
public class TestController {

    /**
     * 注解方法
     */
    @GetMapping("orders/{id}")
    public CommResult<Order> findOrderById(@PathVariable("id") Long id) {
        Order order = new Order();
        order.setId(id);
        order.setName("订单" + id);
        CommResult<Order> result = new CommResult<>();
        result.setBody(order);
        result.setError("successful");
        return result;
    }

    /**
     * 函数式端口
     */
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return route(GET("/persons/{id}"),this::findPersonById);
    }

    private Mono<ServerResponse> findPersonById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Person person = new Person();
        person.setId(Long.parseLong(id));
        person.setAge(15);
        person.setName("ruby-" + id);
        CommResult<Person> result = new CommResult<>();
        result.setError("successful");
        result.setBody(person);

        return ServerResponse
                .status(HttpStatus.OK)
                .body(Mono.just(result), CommResult.class);
    }
}
