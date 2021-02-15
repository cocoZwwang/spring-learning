package pers.cocoadel.learning.spring.bean.circularReference;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class CircularReferenceDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CircularReferenceDemo.class);
        //设置是否允许循环依赖
//        context.setAllowCircularReferences(false);
        context.refresh();

        Student student = context.getBean(Student.class);
        System.out.println(student);

        ClassRoom classRoom = context.getBean(ClassRoom.class);
        System.out.println(classRoom);

        context.close();
    }

    @Bean
    public static Student student() {
        Student student = new Student();
        student.setName("cocoAdel");
        student.setAge(16);
        return student;
    }

    @Bean
    public static ClassRoom classRoom() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName("D110");
        return classRoom;
    }
}
