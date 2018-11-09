package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author wangX
 * @Decription:securirty配置
 * @date 2018/7/24 10:37
 */
@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public CustomUsrService customUsrService() {
        return new CustomUsrService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/styles/**","/scripts/**").permitAll() //指定那些URL不要被保护
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") //登录的时候你要跳转到哪里
                .loginProcessingUrl("/authentication/form")
                .failureUrl("/login?error") //失败页面
                .defaultSuccessUrl("/ayUser/test")
                .permitAll() //登录任意访问
                .and()
                .rememberMe() //rememberMe
                .and() //注销行为任意访问
                .logout()
                .permitAll()
                .and()
                .csrf() //关闭csrf 不然不支持post
                .disable();

       /* //路由策略和访问权限的简单配置
        http.formLogin().//启用登陆
                failureUrl("/login?error").//登陆失败返回URL：/login?error
                defaultSuccessUrl("/ayUser/test").//登陆成功跳转URL，这里跳转到用户首页
                permitAll().//登陆页面全部权限可访问
                and().//注销行为任意访问
                csrf().//关闭csrf 不然不支持post
                disable();*/
        //super.configure(http);

    }

    /**
     * 配置内置用户
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUsrService()).passwordEncoder(new MyPasswordEncoder());
//                .inMemoryAuthentication().
//                withUser("啊毅").password("123456").roles("ADMIN").
//                and()
//                .withUser("啊兰").password("123456").roles("USER");


    }

}
