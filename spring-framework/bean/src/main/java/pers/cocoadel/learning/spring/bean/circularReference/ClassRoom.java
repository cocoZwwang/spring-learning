package pers.cocoadel.learning.spring.bean.circularReference;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Data
public class ClassRoom {
    private String name;

    @Autowired
    private Collection<Student> students;

    @Override
    public String toString() {
        return "ClassRoom{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
