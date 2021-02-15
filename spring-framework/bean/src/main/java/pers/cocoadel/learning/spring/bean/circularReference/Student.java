package pers.cocoadel.learning.spring.bean.circularReference;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class Student {
    private String name;

    private int age;

    @Autowired
    private ClassRoom classRoom;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", classRoom=" + classRoom.getName() +
                '}';
    }
}
