package com.xt.springboot.controller;

import com.xt.springboot.exception.UserNotExistException;
import com.xt.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 这个类的所有方法返回的数据直接写给浏览器，（如果是对象转为 json 数据）
 * @RestController 相当于 @Controller 和 @ResponseBody 一起使用
 */
//@RestController
@Controller
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HelloService helloService;

    @ResponseBody
    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name) {
        return helloService.sayHello(name);
    }

    @ResponseBody
    @GetMapping("/list")
    public List<Map<String, Object>> list() {
        String sql = "select * from department";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        return map;
    }

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
    public String hello(@RequestParam("user") String user) {
        if (user.equals("aaa")) {
            throw new UserNotExistException();
        }
        return "Hello World！" + user;
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
