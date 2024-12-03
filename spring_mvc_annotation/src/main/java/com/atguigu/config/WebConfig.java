package com.atguigu.config;

import com.atguigu.interceptor.FirstInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;

/*
* 代替SpringMVC的配置文件
* 扫描组件、视图解析器、默认的Servlet处理静态资源、mvc的注解驱动、视图控制器、文件上传解析器、拦截器、异常解析器
* */

@Configuration
// 扫描组件
@ComponentScan("com.atguigu.controller")
// mvc的注解驱动
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    // 默认的Servlet处理静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 配置默认的Servlet可用
        configurer.enable();
    }

    // 视图控制器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // addViewController设置请求路径，setViewName设置逻辑视图
        registry.addViewController("/").setViewName("index");
    }

    // 文件上传解析器
    @Bean
    // @Bean可以将标识的方法的返回值作为bean进行管理，bean的id为方法的方法名如multipartResolver
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    // 拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        FirstInterceptor firstInterceptor = new FirstInterceptor();
        // addInterceptor设置拦截器，addPathPatterns设置需要拦截的路径，/**表示任意层目录即所有请求
        registry.addInterceptor(firstInterceptor).addPathPatterns("/**");
    }

    // 异常解析器
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        // 新建异常处理对象
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties prop = new Properties();
        // 键为要捕获的异常类型，值为捕获到异常后跳转的逻辑视图
        prop.setProperty("java.lang.ArithmeticException", "error");
        // 设置类型为Properties的exceptionMappings属性
        exceptionResolver.setExceptionMappings(prop);
        // 设置共享异常信息的请求域中的属性名
        exceptionResolver.setExceptionAttribute("ex");
        // 添加到集合中
        resolvers.add(exceptionResolver);
    }

    // 视图解析器
    // 配置生成模板解析器
    @Bean
    public ITemplateResolver templateResolver() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    // 生成模板引擎并为模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    // 生成视图解析器并为解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

    // 在类内部用了@Bean注解，那么如果方法的形参与另一个Bean的方法名同名
    // 比如public ViewResolver viewResolver(SpringTemplateEngine templateEngine)中
    // 形参templateEngine与public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver)
    // 中的方法名templateEngine同名，那么形参templateEngine就会被赋值为
    // public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver)方法的返回值
}
