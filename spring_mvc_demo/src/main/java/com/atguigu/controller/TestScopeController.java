package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;

/*
*  向域对象共享数据：
*  1、通过ModelAndView向请求域共享数据
*  使用ModelAndView时，可以使用其Model功能向请求域共享数据
*  使用View功能设置逻辑视图，但是控制器方法一定要将ModelAndView作为方法的返回值
*  2、使用Model向请求域共享数据
*  3、使用ModelMap向请求域共享数据
*  4、使用Map向请求域共享数据
*  5、Model和ModelMap和map的关系
*  其实在底层中，这些类型的形参最终都是通过BindingAwareModelMap创建
*  public class BindingAwareModelMap extends ExtendedModelMap
*  public class ExtendedModelMap extends ModelMap implements Model
*  public class ModelMap extends LinkedHashMap<String, Object>
*  public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>
*
*  org.springframework.validation.support.BindingAwareModelMap
* */

@Controller
public class TestScopeController {

    @RequestMapping("/test/mav")
    // 这里不是返回String而是返回ModelAndView对象
    public ModelAndView testMAV() {
        /*
        *   ModelAndView包含Model和View的功能
        *   Model：向请求域中共享数据
        *   View：设置逻辑视图实现页面跳转
        * */
        ModelAndView mav = new ModelAndView();
        // 向请求域中共享数据
        mav.addObject("testRequestScope", "hello,ModelAndView");
        // 设置视图名称 / 逻辑视图（就是跳转到哪个页面）
        mav.setViewName("success");
        return mav;
    }

    @RequestMapping("/test/model")
    public String testModel(Model model) {
        System.out.println("model:" + model.getClass().getName());
        model.addAttribute("testRequestScope", "hello,Model");
        return "success";
    }

    @RequestMapping("/test/modelMap")
    public String testModelMap(ModelMap modelMap) {
        modelMap.addAttribute("testRequestScope", "hello,modelMap");
        return "success";
    }

    @RequestMapping("/test/map")
    public String testMap(Map<String, Object> map) {
        map.put("testRequestScope", "hello,map");
        return "success";
    }

    @RequestMapping("/test/session")
    public String testSession(HttpSession session) {
        session.setAttribute("testSessionScope", "hello,session");
        return "success";
    }

    @RequestMapping("/test/application")
    public String testApplication(HttpSession session) {
        // 获取session所处的最大的域对象
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("testApplicationScope", "hello,application");
        return "success";
    }

}
