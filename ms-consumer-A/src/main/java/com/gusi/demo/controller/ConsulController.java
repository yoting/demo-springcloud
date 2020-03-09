package com.gusi.demo.controller;

import com.gusi.demo.client.MyFeignClient;
import com.gusi.demo.dto.BookDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

/**
 * ConsulController <br>
 * 服务消费的接口，服务提供通过consul
 *
 * @author Lucky
 * @since 2020/2/28
 */
@RestController
@RequestMapping("/consul")
public class ConsulController {
	@Autowired
	private DiscoveryClient consulClient;
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MyFeignClient feignClient;


	@RequestMapping("/services")
	public Object services() {
		return consulClient.getInstances("consul-service1").stream().map(a -> a.getServiceId() + "@" + a.getHost() + ":" + a.getPort()).collect(Collectors.toList());
	}

	@RequestMapping("/discover")
	public Object discover() {
		return loadBalancerClient.choose("consul-service1").getUri();
	}

	/**
	 * 调运服务提供者的接口
	 *
	 * @return
	 */
	@RequestMapping("/call")
	public Object consumerCall() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("consul-service1");
		System.out.println("调用服务地址：" + serviceInstance.getUri());
		System.out.println("调运服务名称：" + serviceInstance.getServiceId());
		return new RestTemplate().getForObject(serviceInstance.getUri() + "/provider/book/get/{id}", BookDto.class, 1);

		// 错误示例：new的restTemplate没有负载均衡能力
		// return new RestTemplate().getForObject("http://consul-service1/provider/book/get/{id}", BookDto.class, 1);
	}

	/**
	 * 通过RestTemplate负载均衡调运
	 *
	 * @return
	 */
	@RequestMapping("/call2")
	public Object consumerCall2() {
		// 通过RestTemplate可以直接在host和port的地方写注册服务的id。
		return restTemplate.getForObject("http://consul-service1/provider/book/get/{id}", BookDto.class, 1);
	}

	/**
	 * 通过Feign负载均衡调运
	 *
	 * @return
	 */
	@RequestMapping("/call3")
	public Object consumerCall3() {
		return feignClient.getBook(1);
	}

	/**
	 * 对方法进行hystrix熔断处理
	 * @return
	 */
	@RequestMapping("/call4")
	@HystrixCommand(fallbackMethod = "getFallbackData")
	public Object consumerCall4() {
		return restTemplate.getForObject("http://consul-service1/provider/book/get/{id}", BookDto.class, 1);
	}

	// hystrix的熔断方法
	public Object getFallbackData() {
		BookDto dto = new BookDto();
		dto.setId(-1);
		dto.setName("hystrix");
		dto.setPrice(0);
		return dto;
	}
}
