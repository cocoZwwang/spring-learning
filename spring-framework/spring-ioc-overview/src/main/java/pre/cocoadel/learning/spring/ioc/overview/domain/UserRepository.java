package pre.cocoadel.learning.spring.ioc.overview.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.core.env.Environment;

import java.util.List;

@ToString
@Data
public class UserRepository {

    private List<User> users;

    private BeanFactory beanFactory;

    private Environment  environment;

    private ObjectFactory<User> userObjectFactory;
}
