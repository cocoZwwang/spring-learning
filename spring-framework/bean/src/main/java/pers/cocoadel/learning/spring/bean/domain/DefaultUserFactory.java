package pers.cocoadel.learning.spring.bean.domain;

import pre.cocoadel.learning.spring.ioc.overview.domain.User;

public class DefaultUserFactory  implements UserFactory{

    @Override
    public User createUser() {
        User user = new User();
        user.setName("Yang XiaoLong");
        user.setAge(16);
        user.setDescription("I am created by DefaultUserFactory");
        return user;
    }
}
