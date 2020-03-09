package com.gusi.demo.client;

import feign.RequestLine;

/**
 * SimpleFeign <br>
 *
 * @author Lucky
 * @since 2020/3/5
 */
public interface SimpleFeign {
	@RequestLine("GET /simple/{id}")
	Object getById(int id);
}

