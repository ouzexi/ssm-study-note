package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestViewController {

    @RequestMapping("/test/view/thymeleaf")
    public String testThymeleafView() {
        return "success";
    }

    @RequestMapping("/test/view/forward")
    public String testInternalResourceView() {
        // 转发视图，路径还是/test/view/forward，页面是/test/model的
        return "forward:/test/model";
    }

    @RequestMapping("/test/view/redirect")
    public String testRedirectView() {
        // 重定向视图，路径变成/test/model的，页面也是/test/model的
        return "redirect:/test/model";
    }
}
