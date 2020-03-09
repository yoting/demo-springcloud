package com.gusi.demo;

import com.gusi.demo.client.AvoidScan;
import com.gusi.demo.config.RibbonConfiguration;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;



/**
 * Application <br>
 *
 * @author Lucky
 * @since 2020/2/27
 */
@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(value = {AvoidScan.class})})
@EnableFeignClients
@RibbonClient(name = "consul-service1", configuration = {RibbonConfiguration.class})
@EnableHystrix
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getResTemplate() {
		return new RestTemplate();
	}
}
