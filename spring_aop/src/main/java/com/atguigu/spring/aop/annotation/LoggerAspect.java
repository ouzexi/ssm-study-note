package com.atguigu.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
*   在切面中，需要通过指定的注解将方法标识为通知方法
*   @Before：标识前置通知的注解，在目标对象方法执行之前执行
*   它的value是必填的，execution指定目标对象的目标执行方法，需要声明public 返回类型 包.类.方法(参数)
*   @After：标识后置通知的注解，在目标对象方法的finally字句中执行
*   @AfterReturning：返回通知，在目标对象方法返回值之后执行
*   @AfterThrowing：异常通知（例外通知），在目标对象方法的catch字句中执行
*
*   切入点表达式：设置在标识通知的注解的value属性中
*   * 表示任意修饰符和返回值 / CalculatorImpl.*表示CalculatorImpl类下的所有方法 / ..表示任意参数列表
*   execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..))
*   第一个*表示任意的访问修饰符和返回值类型
*   第二个*表示类中任意的方法
*   ..表示任意的参数列表
*   类的地方也可以使用*，表示包下所有的类
*
*   获取连接点的信息
*   在通知方法的参数位置，设置JoinPoint类型的参数，就可以获取连接点所对应方法的信息
*   获取连接点所对应的方法的签名信息：
*   Signature signature = joinPoint.getSignature();
*   获取连接点所对应的方法的参数：
*   Object[] args = joinPoint.getArgs();
*
*   重用切入点表达式
*   @Pointcut声明一个公共的切入点表达式
*   @Pointcut("execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..))")
*   public void pointCut() {}
*   使用方式
*   @After("pointCut()")
*
*   切面的优先级
*   可以通过@Order注解的value属性设置优先级，默认值是Integer的最大值
*   @Order注解的value属性值越小，优先级越高
* */

@Component  // @Component注解标识为一个组件，保证这个切面类能够放入IOC容器
@Aspect     // @Aspect表示这个类是一个切面类
public class LoggerAspect {

    @Pointcut("execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..))")
    public void pointCut() {}

    // @Before("execution(public int com.atguigu.spring.aop.annotation.CalculatorImpl.add(int, int))")
    // * 表示任意修饰符和返回值 / CalculatorImpl.*表示CalculatorImpl类下的所有方法 / ..表示任意参数列表
    // @Before("execution(* com.atguigu.spring.aop.annotation.CalculatorImpl.*(..))")
    @Before("pointCut()")
    public void beforeAdviceMethod(JoinPoint joinPoint) {
        // 获取连接点所对应的方法的签名信息
        Signature signature = joinPoint.getSignature();
        // 获取连接点所对应的方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("LoggerAspect，方法：" + signature.getName() + "，参数：" + Arrays.toString(args));
    }

    @After("pointCut()")
    public void afterAdviceMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect，方法：" + signature.getName() + "后置通知");
    }

    // 在返回通知中，若要获取目标对象方法的返回值，
    // 只需要通过@AfterReturning注解的returning属性，
    // 就可以将通知方法的某个参数指定为接收目标对象方法的返回值的参数
    // 说白了就是returning的参数名(result)要和通知方法的参数名(Object result)一致
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturningAdviceMethod(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect，方法：" + signature.getName() + "返回通知，结果：" + result);
    }

    // 在异常通知中，若要获取目标对象方法的异常，
    // 只需要通过@AfterThrowing注解的throwing属性，
    // 就可以将通知方法的某个参数指定为接收目标对象方法出现的异常的参数
    // 说白了就是throwing的参数名(ex)要和通知方法的参数名(Throwable ex)一致
    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Throwable ex) {
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect，方法：" + signature.getName() + "，异常通知：" + ex);
    }

    @Around("pointCut()")
    // 环绕通知的方法的返回值一定要和目标对象方法的返回值一致（因为是经过代理对象的方法(aroundAdviceMethod)间接访问目标对象的功能）
    public Object aroundAdviceMethod(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            System.out.println("环绕通知-->前置通知");
            // 表示目标对象方法的执行
            result = joinPoint.proceed();
            System.out.println("环绕通知-->返回通知");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知-->异常通知");
        } finally {
            System.out.println("环绕通知-->后置通知");
        }
        return result;
    }

}
