package pers.cocoadel.learning.spring.environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceTest.class)
@TestPropertySource(
        properties = "user.name = yang",
        locations = "classpath:/META-INF/userProperties.default"
)
public class TestPropertySourceTest {

    @Autowired
    private ConfigurableEnvironment environment;

    @Value("${user.name}")
    private String userName;

    @Test
    public void testPropertySource() {
        assertEquals("yang", userName);
        for (PropertySource<?> source : environment.getPropertySources()) {
            System.out.printf("propertySource（name=%s）, user.name = %s\n",source.getName(),source.getProperty("user.name"));
        }
    }
}
