package gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


import reactor.core.publisher.Mono;
@Component
public class AuthenticationFilter implements GatewayFilter {
	
	@Autowired
	private JWTUtil jwtUtil;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		// request from exchange
		
		ServerHttpRequest request = exchange.getRequest();
		
		// authorization token 
		
		if(!request.getHeaders().containsKey("Authorization")) {
			ServerHttpResponse response = exchange.getResponse();
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			return response.setComplete();
		}
		
		final String token = request.getHeaders().getOrEmpty("Authorization").get(0);
		System.out.println("token is " + token);
		
		// validate token
		jwtUtil.validateToken(token);
		
		
		return chain.filter(exchange);
	}

}
