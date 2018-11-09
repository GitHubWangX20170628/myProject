package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Decription:spring boot 启动类
 * @author wangX
 * @date 2018/7/17 16:21
 *
 */
/**
 * @ServletComponentScan 使用该注解，Servlet,Filter,Listener可以直接通过@WebServlet，@WebFilter,@WebListener注解自动注册，无需其他代码
 * @ImportResource 加载xml文件
 * @EnableAsync 开启异步调用
 * @EnableRetry 开启retry重拾
 */
@SpringBootApplication
@ServletComponentScan
@ImportResource(locations = "classpath:spring-mvc.xml")
@EnableAsync
@EnableRetry
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
