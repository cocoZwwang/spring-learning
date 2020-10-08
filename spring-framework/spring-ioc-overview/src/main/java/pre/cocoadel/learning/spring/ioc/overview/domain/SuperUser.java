package pre.cocoadel.learning.spring.ioc.overview.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pre.cocoadel.learning.spring.ioc.overview.annotation.Super;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Super
public class SuperUser extends User{

    private String address;
}
