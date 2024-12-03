package com.atguigu.spring.controller;

import com.atguigu.spring.service.UserService;

public class UserController {

    // private UserService userService = new UserServiceImpl()
    // 以上硬编码有缺陷，如果接口实现类改变了，需要进入每个用到UserService的文件中更改，
    // 所以以下利用IOC通过setter管理实现类，UserService被动接受IOC的注入
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void saveUser() {
        userService.saveUser();
    }
}
