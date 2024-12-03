package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/")
    // 斜线/被服务器解析，是作为绝对路径相当于http://localhost:8080/SpringMVC/
    public String protal() {
        // 将逻辑视图返回
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "success";
    }
}
