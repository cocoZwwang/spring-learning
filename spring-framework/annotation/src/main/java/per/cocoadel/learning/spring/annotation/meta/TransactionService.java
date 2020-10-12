package per.cocoadel.learning.spring.annotation.meta;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
/**
 * 保留的意思，保留策略
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
@Service
@Transactional
public @interface TransactionService {

    /**
     * 同value()相互别名
     * return Bean名称
     */
    @AliasFor("value")
    String name() default "";

    /**
     * 同name相互别名
     * 覆盖了{@link Transactional#value()}
     * @return Bean名称
     */
    @AliasFor("name")
    String value() default "";

    /**
     * 显性覆盖{@link Transactional#transactionManager}
     * @return
     */
    @AliasFor(attribute = "transactionManager", annotation = Transactional.class)
    String manager() default "txManager";

}
