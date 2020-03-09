package com.gusi.demo.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * BookDto <br>
 *
 * @author Lucky
 * @since 2020/2/27
 */
@Component
//@RefreshScope
@ConfigurationProperties(prefix = "book")
public class BookCfg {
	private int id;
	private String name;
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}