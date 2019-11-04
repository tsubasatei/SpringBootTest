package com.xt.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * 这个类的所有方法返回的数据直接写给浏览器，（如果是对象转为 json 数据）
 * @RestController 相当于 @Controller 和 @ResponseBody 一起使用
 */
//@RestController
@Controller
public class HelloController {

    /**
     * 用配置类替代
     * @return
     */
    /*@GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }*/

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello World！";
    }

    /**
     * 查出用户数据，在页面展示
     * @param map
     * @return
     */
    @GetMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("hello", "<h1>你好！</h1>");
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        // classpath:/templates/success.html
        return "success";
    }
}
