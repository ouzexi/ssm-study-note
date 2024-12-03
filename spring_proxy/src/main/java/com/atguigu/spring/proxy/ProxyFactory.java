package com.atguigu.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyFactory {

    // 因为它适用于所有类型的对象，所以类型为Object
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 动态生成的，不确定类型，所以返回值为Object
    public Object getProxy() {
        /*
        *   ClassLoader loader：指定加载动态生成的类加载器（根类加载器、拓展类加载器、应用类加载器（✔）、自定义加载器）（因为类想要被执行就必须要先被加载）
        *   Class<?>[] interfaces：获取目标对象实现的所有的接口的class对象的数组（因为当前这个对象所对应的动态生成的类必须跟目标类实现相同的接口）
        *   InvocationHandler h：设置代理类中的抽象方法如何重写（比如在核心代码前后添加打印日志的代码）
        * */
        // ClassLoader classLoader = ProxyFactory.class.getClassLoader(); 获取ProxyFactory这个类的类加载器
        ClassLoader classLoader = this.getClass().getClassLoader(); // 等价上面
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 接口匿名内部类，因为InvocationHandler是接口，所以不能直接new对象，所以可以通过接口匿名内部类重写方法创建对象
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    System.out.println("日志，方法：" + method.getName() + "参数：" + Arrays.toString(args)); // 因为args是对象数组类型，所以需要Arrays.toString逐个转成字符串
                    // proxy表示代理对象，method表示要执行的方法，args表示要执行的方法的参数列表
                    // method.invoke表示执行要执行的方法，第一个参数表示哪个对象（目标对象target）需要执行该方法，第二个参数是传递给方法的参数
                    result = method.invoke(target, args);
                    System.out.println("日志，方法：" + method.getName() + "结果：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("日志，方法：" + method.getName() + "异常：" + e);
                }  finally {
                    System.out.println("日志，方法：" + method.getName() + "方法执行完毕");
                }
                return result;
            }
        };
        return Proxy.newProxyInstance(classLoader, interfaces, h);
    }


}
