package com.atguigu.spring.test;

import com.atguigu.spring.pojo.Clazz;
import com.atguigu.spring.pojo.Person;
import com.atguigu.spring.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCByXMLTest {

    /*
    *   获取bean的三种方式：
    *   1、根据bean的id获取
    *   2、根据bean的类型获取
    *   注意：根据类型获取bean时，要求IOC容器中有且只有一个类型匹配的bean
    *   若没有任何一个类型匹配的bean，此时抛出异常：NoSuchBeanDefinitionException
    *   若有多个类型匹配的bean，此时抛出异常：NoUniqueBeanDefinitionException
    *   3、根据bean的id和类型获取
    *   结论
    *   根据类型来获取bean时，在满足bean唯一性的前提下
    *   其实只是看：『对象 instanceof 指定的类型』的返回结果
    *   只要返回的是true就可以认定为和类型匹配，能够获取到。
    *   即通过bean的类型、bean所继承的类的类型、bean所实现接口的类型都可以获取bean
    * */

    @Test
    public void testIOC() {
        // 获取ioc容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-ioc.xml");
        // 获取ioc容器的bean
        // Student student = (Student) ioc.getBean("studentOne");
        // Student student = ioc.getBean(Student.class);
        // Student student = ioc.getBean("studentOne", Student.class);
        // 可以获取bean的父类或接口，如下例是通过类型所实现的接口获取bean
        Person person = ioc.getBean(Person.class);
        // 因为只创建了对象，没有为对象的成员变量赋值，所以属性都是null，Student{sid=null, sname='null', age='null', gender='null'}
        System.out.println(person);
    }

    @Test
    public void testDI() {
        // 获取ioc容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-ioc.xml");
        // 获取ioc容器的bean
        Student student = ioc.getBean("studentSix", Student.class);
        System.out.println(student);

        /*Clazz clazz = ioc.getBean("clazzOne", Clazz.class);
        System.out.println(clazz);*/
    }

}
