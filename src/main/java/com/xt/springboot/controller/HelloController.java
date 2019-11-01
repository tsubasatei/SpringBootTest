package com.xt.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这个类的所有方法返回的数据直接写给浏览器，（如果是对象转为 json 数据）
 * @RestController 相当于 @Controller 和 @ResponseBody 一起使用
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World！";
    }
}
