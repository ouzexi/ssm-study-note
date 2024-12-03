package com.atguigu.spring.test;

import com.atguigu.spring.pojo.HelloWorld;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldTest {

    @Test
    public void test() {
        // 获取IOC容器 - ClassPathXmlApplicationContext类参数为spring配置文件路径
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取IOC容器中的bean对象 - 因为通过id获取不知道它的类型，所以可以通过(HelloWorld)强制转换类型
        HelloWorld helloWorld = (HelloWorld) ioc.getBean("helloworld");
        helloWorld.sayHello();
    }

}
