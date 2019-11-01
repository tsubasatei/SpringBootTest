package com.xt.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @SpringBootApplication : 标注一个主程序类，说明这是一个 Spring Boot 应用
 *
 * @ImportResource(locations = {"classpath:beans.xml"}) :
 *      导入 Spring 的配置文件，让配置文件里面的内容生效，标注在配置类上，不推荐这种写法
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
