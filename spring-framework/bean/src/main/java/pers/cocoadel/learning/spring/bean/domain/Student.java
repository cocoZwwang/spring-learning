package pers.cocoadel.learning.spring.bean.domain;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

@Data
public class Student implements InitializingBean {
    private String name;

    private int age;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.printf("Student{%s} Bean init on afterPropertiesSet...\n",name);
    }

    @PostConstruct
    public void onPostConstruct(){
        System.out.printf("Student{%s} Bean init on onPostConstruct...\n",name);
    }


    public void init(){
        System.out.printf("Student{%s} Bean init on customer method....\n",name);
    }

}
