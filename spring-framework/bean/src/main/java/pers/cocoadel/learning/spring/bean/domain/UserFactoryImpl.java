package pers.cocoadel.learning.spring.bean.domain;

import pre.cocoadel.learning.spring.ioc.overview.domain.User;

public class UserFactoryImpl implements UserFactory{

    @Override
    public User createUser() {
        User user = new User();
        user.setName("Black Belladonna");
        user.setAge(15);
        user.setDescription("I am created by UserFactoryImpl");
        return user;
    }
}
