package com.gusi.demo.controller;

import com.gusi.demo.dto.BookCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConfigController <br>
 *
 * @author Lucky
 * @since 2020/3/3
 */
@RestController
//@RefreshScope
public class ConfigController {
	@Autowired
	BookCfg bookCfg;

	@Value("${myname}")
	String myName;

	@GetMapping("/getBookCfg")
	public Object getBookCfg() {
		return bookCfg;
	}

	@GetMapping("/getMyname")
	public Object getMyname() {
		return myName;
	}
}
