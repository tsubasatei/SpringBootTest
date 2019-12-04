package com.xt.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @SpringBootApplication : 标注一个主程序类，说明这是一个 Spring Boot 应用
 *
 * @ImportResource(locations = {"classpath:beans.xml"}) :
 *      导入 Spring 的配置文件，让配置文件里面的内容生效，标注在配置类上，不推荐这种写法
 *
 * springboot 启动会扫描以下位置的 application.properties 或者 application.yml 文件作为 Spring boot 的默认配置文件
 *      项目路径下:  –file:./config/
 *                  –file:./
 *      类路径下：   –classpath:/config/
 *                  –classpath:/
 *      优先级由高到底，高优先级的配置会覆盖低优先级的配置；
 *      SpringBoot会从这四个位置全部加载主配置文件；互补配置；
 *
 *
 * 一、搭建基本环境
 * 1、导入数据库文件 创建出department和employee表
 * 2、创建javaBean封装数据
 * 3、整合MyBatis操作数据库
 * 		1.配置数据源信息
 * 		2.使用注解版的MyBatis；
 * 			1）、@MapperScan指定需要扫描的mapper接口所在的包
 *
 * 二、快速体验缓存
 * 		步骤：
 * 			1、开启基于注解的缓存 @EnableCaching
 * 			2、标注缓存注解即可
 * 				@Cacheable
 * 				@CacheEvict
 * 				@CachePut
 * 默认使用的是 ConcurrentMapCacheManager==ConcurrentMapCache；将数据保存在	ConcurrentMap<Object, Object>中
 * 开发中使用缓存中间件；redis、memcached、ehcache；
 *
 * 三、整合 redis 作为缓存
 * Redis 是一个开源（ BSD 许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。
 * 	1、安装 redis：使用 docker；
 * 	2、引入 redis 的 starter
 * 	3、配置 redis
 * 	4、测试缓存
 * 		原理：CacheManager === Cache 缓存组件来实际给缓存中存取数据
 *		1）、引入 redis 的 starter，容器中保存的是 RedisCacheManager；
 *		2）、RedisCacheManager 帮我们创建 RedisCache 来作为缓存组件；RedisCache 通过操作 redis 缓存数据的
 *		3）、默认保存数据 k-v 都是 Object；利用序列化保存；如何保存为j son
 *   			1、引入了 redis 的starter，cacheManager 变为 RedisCacheManager；
 *   			2、默认创建的 RedisCacheManager 操作 redis的时候使用的是 RedisTemplate<Object, Object>
 *   			3、RedisTemplate<Object, Object> 是 默认使用 jdk 的序列化机制
 *      4）、自定义 CacheManager；
 *
 */
//@ImportResource(locations = {"classpath:beans.xml"})
@MapperScan("com.xt.springboot.mapper")
@SpringBootApplication
@EnableCaching
public class SpringBootTestApplication {

    public static void main(String[] args) {

        /**
         * Spring 应用启动起来
         */
        SpringApplication.run(SpringBootTestApplication.class, args);
    }

}
