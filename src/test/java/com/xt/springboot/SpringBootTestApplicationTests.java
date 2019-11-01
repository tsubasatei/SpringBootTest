package com.xt.springboot;

import com.xt.springboot.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

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

    @Test
    public void testHelloService () {
        boolean b = ioc.containsBean("helloService");
        System.out.println("ioc容器中是否有 HelloService: " + b);

    }

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
