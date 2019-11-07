package com.xt.springboot.component;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 给容器中加入我们自己定义的 ErrorAttributes
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    /**
     * 解决定制错误数据时无法获取并显示异常
     * server.error.include-exception=true
     *
     * @param serverProperties
     */
    public MyErrorAttributes(ServerProperties serverProperties) {
        super(serverProperties.getError().isIncludeException());
    }

    /**
     * 返回值的 map 就是页面和 json 能获取的所有字段
     * @param webRequest
     * @param includeStackTrace
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company", "IBM");

        // 自定义异常处理类所携带的数据
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        map.put("ext", ext);
        return map;
    }
}
