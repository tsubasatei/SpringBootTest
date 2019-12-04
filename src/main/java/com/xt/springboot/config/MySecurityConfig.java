package com.xt.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 1、引入 SpringSecurity；
 * 2、编写 SpringSecurity 的配置类；
 * 		@EnableWebSecurity   extends WebSecurityConfigurerAdapter
 * 3、控制请求的访问权限：
 * 		configure(HttpSecurity http) {
 * 		 	http.authorizeRequests().antMatchers("/").permitAll()
 * 		 		.antMatchers("/level1/**").hasRole("VIP1")
 * 		}
 * 4、定义认证规则：
 * 		configure(AuthenticationManagerBuilder auth){
 * 		 	auth.inMemoryAuthentication()
 * 		 		.withUser("zhangsan").password("123456").roles("VIP1","VIP2")
 * 		}
 * 5、开启自动配置的登陆功能：
 * 		configure(HttpSecurity http){
 * 		 	http.formLogin();
 * 		}
 * 6、注销：http.logout();
 * 7、记住我：Remeberme()；
 */
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        /**
         * 开启自动配置的登陆功能，效果: 如果没有登陆，没有权限就会来到登陆页面
         *
         * 1、/login来到登陆页
         * 2、重定向到 /login?error表示登陆失败
         * 3、更多详细规定
         * 4、默认 post 形式的 /login代表处理登陆
         * 5、一旦定制 loginPage；那么 loginPage的 post请求就是登陆
         */
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userLogin");

        /**
         * 开启自动配置的注销功能。
         * 1、访问 /logout 表示用户注销，清空 session
         * 2、注销成功会返回 /login?logout 页面；
         */
        http.logout().logoutSuccessUrl("/"); // 注销成功后来到首页

        /**
         * 开启记住我功能
         *
         * 登陆成功以后，将 cookie 发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
         * 点击注销会删除cookie
         */
        http.rememberMe().rememberMeParameter("remember");

//                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
    }


    // 定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // There is no PasswordEncoder mapped for the id "null"
        /*auth.inMemoryAuthentication() // 从内存中获取
                .withUser("zhangsan").password("123456").roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password("123456").roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu").password("123456").roles("VIP1", "VIP3");*/

        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("zhangsan")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("VIP1", "VIP2")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("lisi")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("VIP1", "VIP3")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("wangwu")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("VIP2", "VIP3");

    }
}
