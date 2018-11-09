package com.example.demo.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @Decription:自定义错误页面
 * @author wangX
 * @date 2018/7/23 11:21
 *
 */
@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {
    /*@Bean
    public EmbeddedServletContainerCustomizer  containerCustomizer(){
        return new ErrorPageRegistrar (){

        }
    }*/

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        registry.addErrorPages(e404);
    }
}
