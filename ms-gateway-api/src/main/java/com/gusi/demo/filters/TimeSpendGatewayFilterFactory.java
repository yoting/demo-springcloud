package com.gusi.demo.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * gateway请求耗时记录过滤器（自定义过滤器）工厂 <br>
 *
 * @author Lucky
 * @since 2020/3/2
 */
@Component
public class TimeSpendGatewayFilterFactory extends AbstractGatewayFilterFactory<TimeSpendGatewayFilterFactory.Config> {

	public TimeSpendGatewayFilterFactory() {
		super(Config.class); //如果有额外参数，需要调运该构造器，不然默认使用的是Object类型，会导致类型转换异常
	}

	@Override
	public GatewayFilter apply(final Config config) {
		return new GatewayFilter() {
			@Override
			public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
				ServerHttpRequest request = exchange.getRequest().mutate().build();


				final String requestId = request.getId();

				//此处相当于pre请求之前执行的任务
				System.err.println("TimeSpendGatewayFilterFactory-" + config.getPrefix() + ":" + requestId + "[begin]" + System.currentTimeMillis());

				//then相当于post后执行的任务
				return chain.filter(exchange).then(Mono.<Void>fromRunnable(new Runnable() {
					@Override
					public void run() {
						System.err.println("TimeSpendGatewayFilterFactory-" + config.getPrefix() + ":" + requestId + "[begin]" + System.currentTimeMillis());
					}
				}));
				//exchange.mutate().request(request).build();//如果reques改变后需要用到这句话
			}
		};
	}


	/**
	 * 过滤器的配置
	 */
	public static class Config {
		private String prefix;

		public String getPrefix() {
			return prefix;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}
	}
}
