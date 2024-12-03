package com.atguigu.spring.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// BeanPostProcessor有2个方法，MyBeanProcessor implements BeanPostProcessor的时候没报错，说明有默认方法体，通过Ctrl+o选择重写两个方法
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 此方法在bean的生命周期初始化之前执行
        System.out.println("MyBeanPostProcessor--->后置处理器postProcessBeforeInitialization");
        // 这个bean就是IOC容器中管理的bean，beanName就是bean的id
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 此方法在bean的生命周期初始化之后执行
        System.out.println("MyBeanPostProcessor--->后置处理器postProcessAfterInitialization");
        return null;
    }
}
