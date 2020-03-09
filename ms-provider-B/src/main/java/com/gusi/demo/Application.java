package com.gusi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Application <br>
 *
 * @author Lucky
 * @since 2020/2/27
 */
@SpringBootApplication(scanBasePackages={"com.gusi.demo"})
@EnableDiscoveryClient //开启服务注册和发现
public class Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
