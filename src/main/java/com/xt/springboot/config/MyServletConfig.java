package com.xt.springboot.config;

import com.xt.springboot.filter.MyFilter;
import com.xt.springboot.listener.MyListener;
import com.xt.springboot.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class MyServletConfig {

    /**
     * 注册三大组件
     */
    @Bean
    public ServletRegistrationBean myServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/hello", "/myServlet");

        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListen() {
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }

    /**
     * 配置嵌入式的 Servlet 容器
     *
     * Spring Boot2.0以上配置嵌入式 Servlet容器时
     *      EmbeddedServletContainerCustomizer类不存在，
     *      被 WebServerFactoryCustomizer 替代
     *      WebServerFactoryCustomizer接口中使用 ConfigurableWebServerFactory对象实现对 customize()方法的转换
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        // 定制嵌入式的Servlet容器相关的规则
        return factory -> factory.setPort(8090);
    }
}
