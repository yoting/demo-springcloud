package com.gusi.demo;

import com.gusi.demo.client.SimpleFeign;
import com.gusi.demo.dto.BookDto;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 */
public class App {

	public static void testRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		BookDto book = restTemplate.getForObject("http://LAPTOP-2D57GMV4:8882/provider/book/get/{id}", BookDto.class, 1);
		System.out.println(book);
	}

	public static void testFeign() {
		//获取feign客户端进行相关配置后调运接口
		SimpleFeign feignClient = Feign.builder()
				.options(new Request.Options(1000, 3500))
				.retryer(new Retryer.Default(5000, 5000, 3))
				.target(SimpleFeign.class, "http://127.0.0.1:8888");
		Object result = feignClient.getById(1);
	}
}
