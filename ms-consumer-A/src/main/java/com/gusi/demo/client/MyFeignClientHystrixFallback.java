package com.gusi.demo.client;

import com.gusi.demo.dto.BookDto;
import org.springframework.stereotype.Component;

/**
 * MyFeignClientHystrixFallback <br>
 *
 * @author Lucky
 * @since 2020/3/8
 */
@Component
public class MyFeignClientHystrixFallback implements MyFeignClient {
	@Override
	public BookDto getBook(int id) {
		BookDto dto = new BookDto();
		dto.setId(-1);
		dto.setName("hystrix");
		dto.setPrice(0);
		return dto;
	}
}
