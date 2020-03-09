package com.gusi.demo;

import com.gusi.demo.dto.BookCfg;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Application <br>
 *
 * @author Lucky
 * @since 2020/3/3
 */
@SpringBootApplication
@EnableConfigurationProperties({BookCfg.class})
public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class);
		System.out.println(context.getClass().getName());
		System.out.println(AutoConfigurationPackages.get(context.getBeanFactory()));
	}
}
