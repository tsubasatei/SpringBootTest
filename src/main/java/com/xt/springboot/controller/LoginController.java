package com.xt.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        Map<String, Object> map, HttpSession session) {

        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            session.setAttribute("loginUser", username);
            // 登录成功，防止表单重复提交，可以重定向到主页
            return "redirect:/main.html";
        }
        map.put("msg", "用户名为空或密码不正确");
        return "login";

    }
}
