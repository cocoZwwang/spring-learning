package pres.cocoadel.spring.learning.externalized.configuration;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
//@TestPropertySource#properties 优先级最高
//@SpringBootTest#properties 优先级第二
//TestPropertySource#locations 优先级第三
@RunWith(SpringRunner.class)
@TestPropertySource(
//        properties = "user.id = 10",
        locations = "classpath:/META-INF/default.properties"
)
@SpringBootTest(
//        properties = "user.id = 20",
        classes = PropertiesSourceOrderTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Configuration
@PropertySource(name = "test-property-source",value = "classpath:/META-INF/test.properties")
public class PropertiesSourceOrderTest {

    @Value("${user.id}")
    private String userId;

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ConfigurableApplicationContext context;

    @Test
    public void testEnvironment() {
        Assert.assertSame(environment, context.getEnvironment());
    }

    @Test
    public void testProperties() {
        environment.getPropertySources().forEach(propertySource -> {
            System.out.printf("ProperSource[名称：%s] - %s \n", propertySource.getName(), propertySource);
        });
    }

    @Test
    public void userTest() {
        System.out.println("userId:" + userId);
//        Assert.assertEquals(userId,);
    }
}
