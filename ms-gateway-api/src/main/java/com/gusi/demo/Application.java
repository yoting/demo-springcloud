package com.gusi.demo;

import com.gusi.demo.filters.TimeSpendGatewayFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * Application <br>
 *
 * @author Lucky
 * @since 2020/3/1
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

//	@Bean
//	public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes().route((r) -> {
//			r.path("baidu").uri("http://baidu.com:80")
//		}).build();
//
//	}

	@Bean
	public TimeSpendGatewayFilterFactory getTimeSpendFilter() {
		return new TimeSpendGatewayFilterFactory();
	}
}
