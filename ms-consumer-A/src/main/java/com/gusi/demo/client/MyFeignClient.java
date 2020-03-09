package com.gusi.demo.client;

import com.gusi.demo.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ProviderClient <br>
 *
 * @author Lucky
 * @since 2020/3/4
 */
@FeignClient(name = "consul-service1", fallback = MyFeignClientHystrixFallback.class)
public interface MyFeignClient {
	@RequestMapping("/provider/book/get/{id}")
	BookDto getBook(@PathVariable("id") int id);
}
