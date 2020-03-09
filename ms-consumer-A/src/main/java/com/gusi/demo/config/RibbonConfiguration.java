package com.gusi.demo.config;

import com.gusi.demo.client.AvoidScan;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RibbonConfiguration <br>
 *
 * @author Lucky
 * @since 2020/3/5
 */
@AvoidScan //避免该类被扫描
@Configuration
public class RibbonConfiguration {
	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}

//	@Bean
//	public ServerList<Server> ribbonServerList() {
//		List<Server> list = new ArrayList();
//		if (!Strings.isNullOrEmpty(listOfServers)) {
//			for (String s: listOfServers.split(",")) {
//				list.add(new Server(s.trim()));
//			}
//		}
//		return new StaticServerList<Server>(list);
//	}
}
