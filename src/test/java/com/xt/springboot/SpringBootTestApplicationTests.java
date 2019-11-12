package com.xt.springboot;

import com.xt.springboot.bean.Person;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SpringBoot 单元测试
 * 可以在测试期间很方便的类似编码一样进行自定注入等容器的功能
 */
@SpringBootTest
class SpringBootTestApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Autowired
    DataSource dataSource;

    // 记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 注意： @Test 引用的是 org.junit.jupiter.api.Test
     * 引用错误，没有可运行标识
     */
    @Test
    public void testHelloService () {
        boolean b = ioc.containsBean("helloService");
        System.out.println("ioc容器中是否有 HelloService: " + b);

    }

    @Test
    void contextLoads() throws SQLException {

        // class com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        // HikariProxyConnection @1728726840 wrapping com.mysql.cj.jdbc.ConnectionImpl@47ffdbba
        System.out.println(connection);
        connection.close();
        /**
         * 日志级别：
         * 由低到高：trace < debug < info < warn < error
         * 可以调整输出的日志级别：日志就只会在这个级别及以后的高级别生效
         * SpringBoot 默认给我们使用的是 info 级别的
         * 没有指定级别的就用SpringBoot默认规定的级别；也叫 root 级别
         */
        logger.trace("trace 级别的日志");
        logger.debug("debug 级别的日志");
        logger.info("info 级别的日志");
        logger.warn("warn 级别的日志");
        logger.error("error 级别的日志");
        System.out.println(person);

    }

}
