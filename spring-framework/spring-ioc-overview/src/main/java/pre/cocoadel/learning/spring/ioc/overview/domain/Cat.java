package pre.cocoadel.learning.spring.ioc.overview.domain;

import lombok.Data;
import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@ToString
@Data
public class Cat {
    private String name;
    private int age;
    private String description;

    public Cat() {
    }

    public Cat(String name, int age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    @PostConstruct
    public void init() {
        System.out.println("Cat Bean init on PostConstruct...");
    }

    @PreDestroy
    public void onDestroy(){
        System.out.println("Cat Bean destroy...");
    }
}
