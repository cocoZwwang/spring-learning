package pre.cocoadel.learning.spring.ioc.overview.domain;

import lombok.Data;
import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@ToString
@Data
public class User {
    private String name;

    private Integer age;

    private String description;

    public User() {
    }

    public User(String name, Integer age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    @PostConstruct
    public void init(){
        System.out.println("User Bean init on PostConstruct...");
    }

    @PreDestroy
    public void onDestroy(){
        System.out.println("User Bean destroy...");
    }

    public static User createUser() {
        User user = new User();
        user.setName("RWBY");
        user.setAge(15);
        user.setDescription("I am created by the static method createUser!");
        return user;
    }
}
