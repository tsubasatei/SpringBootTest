package com.xt.springboot;

import com.xt.springboot.bean.Employee;
import com.xt.springboot.bean.Person;
import com.xt.springboot.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    @Autowired
    StringRedisTemplate stringRedisTemplate; // 操作 k-v 都是字符串的

    @Autowired
    RedisTemplate<Object, Object> redisTemplate; // 操作 k-v 都是对象的

    @Autowired
    EmployeeMapper employeeMapper;


    // 记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Redis常见的五大数据类型
     *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     *  stringRedisTemplate.opsForValue()[String（字符串）]
     *  stringRedisTemplate.opsForList()[List（列表）]
     *  stringRedisTemplate.opsForSet()[Set（集合）]
     *  stringRedisTemplate.opsForHash()[Hash（散列）]
     *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
     */
    @Test
    public void testStringRedisTemplate () {

        // 给 redis 中保存数据
//        stringRedisTemplate.opsForValue().append("msg", "ohayo");
//        String msg = stringRedisTemplate.opsForValue().get("msg");
//        System.out.println(msg);

        stringRedisTemplate.opsForList().leftPush("myList", "1");
        stringRedisTemplate.opsForList().leftPush("myList", "2");

        List<String> myList = stringRedisTemplate.opsForList().range("myList", 0, -1);
        System.out.println(myList);
    }

    // 测试保存对象
    @Test
    public void testRedisTemplate () {
        Employee employee = employeeMapper.getById(2);
//        默认如果保存对象，使用 jdk 序列化机制，序列化后的数据保存到 redis 中
        redisTemplate.opsForValue().set("emp-02", employee);

        Object o = redisTemplate.opsForValue().get("emp-02");
        System.out.println(o);

        /**
         * 将数据以 json 的方式保存
         * （1）自己将对象转为 json
         * （2） redisTemplate 默认的序列化规则: 改变默认的序列化规则
         */
    }

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
