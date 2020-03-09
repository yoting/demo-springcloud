package com.gusi.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * AppController <br>
 *
 * @author Lucky
 * @since 2020/2/28
 */
@RequestMapping
@Controller
public class AppController {
	@RequestMapping("/")
	@ResponseBody
	public  String index(){
		return "hello consumer.";
	}
}
