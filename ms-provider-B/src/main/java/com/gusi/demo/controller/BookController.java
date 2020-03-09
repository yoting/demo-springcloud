package com.gusi.demo.controller;

import com.gusi.demo.entity.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BookController <br>
 *
 * @author Lucky
 * @since 2020/2/27
 */
@RestController
@RequestMapping("/book")
public class BookController {

	@RequestMapping("/get/{id}")
	public Book getBook(@PathVariable int id) {
		System.out.println(id);
		Book b = new Book();
		b.setId(id);
		b.setName("SpringCloudActionFromB");
		b.setPrice(9.9);
		return b;
	}
}
