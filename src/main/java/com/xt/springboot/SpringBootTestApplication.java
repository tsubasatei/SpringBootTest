package com.xt.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @SpringBootApplication : 标注一个主程序类，说明这是一个 Spring Boot 应用
 *
 * @ImportResource(locations = {"classpath:beans.xml"}) :
 *      导入 Spring 的配置文件，让配置文件里面的内容生效，标注在配置类上，不推荐这种写法
 *
 * springboot 启动会扫描以下位置的 application.properties 或者 application.yml 文件作为 Spring boot 的默认配置文件
 *      项目路径下:  –file:./config/
 *                  –file:./
 *      类路径下：   –classpath:/config/
 *                  –classpath:/
 *      优先级由高到底，高优先级的配置会覆盖低优先级的配置；
 *      SpringBoot会从这四个位置全部加载主配置文件；互补配置；
 */
//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class SpringBootTestApplication {

    public static void main(String[] args) {

        /**
         * Spring 应用启动起来
         */
        SpringApplication.run(SpringBootTestApplication.class, args);
    }

}
