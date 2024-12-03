package com.atguigu.spring.test;

import com.atguigu.spring.aop.annotation.Calculator;
import com.atguigu.spring.aop.annotation.CalculatorImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

    @Test
    public void testAOPByAnnotation() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("aop-annotation.xml");
        // 获取的是代理对象，因为不能直接通过目标对象来访问
        // 使用它继承的父类或者实现的接口就可以
        Calculator calculator = ioc.getBean(Calculator.class);
        calculator.div(10, 1);
    }

}
