package com.xt.springboot.controller;

import com.xt.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 1. 浏览器、客户端 返回的都是 json
     * @param e
     * @return
     */
    /*@ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String, Object> handleException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notExist");
        map.put("message", e.getMessage());
        return map;
    }*/

    /**
     * 2. 转发到/error进行自适应响应效果处理
     *
     * handleException：会处理所有 Controller 层抛出的 UserNotExistException 及其子类的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(UserNotExistException e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        /**
         * 传入自己的错误状态码 4xx、5xx, 否则就不会进入定制错误页面的解析流程
         * Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user.notExist");
        map.put("message", "用户出错了");
        request.setAttribute("ext", map);
        return "forward:/error";
    }
}
