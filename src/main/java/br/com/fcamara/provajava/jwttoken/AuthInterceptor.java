package br.com.fcamara.provajava.jwttoken;

import java.util.List;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.fcamara.provajava.exception.InvalidCredentialsException;
import br.com.fcamara.provajava.helper.SessaoManager;
import reactor.core.publisher.Mono;

public class AuthInterceptor implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		if ( !exchange.getRequest().getMethodValue().contains("OPTIONS") && !exchange.getRequest().getPath().value().contains("/rest/auth") ) {
			
			List<String> headerAuthorizationList = exchange.getRequest().getHeaders().get("Authorization");
			TokenManager hcfTokenManager = new TokenManager();
			DecodedJWT jwt = hcfTokenManager.getToken(headerAuthorizationList == null || headerAuthorizationList.isEmpty() ? "" : headerAuthorizationList.get(0));
			
			String oldToken = SessaoManager.getInstancia().getSessoes().get(jwt.getClaim("login").asString());
			if ( oldToken == null || !oldToken.equals(headerAuthorizationList.get(0)) )
				throw new InvalidCredentialsException();
			
	        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) ((AnnotationConfigReactiveWebServerApplicationContext)exchange.getApplicationContext()).getBeanFactory();
	        
	        if ( beanFactory.containsBeanDefinition("userToken") )
	        	beanFactory.removeBeanDefinition("userToken");
	        
	        beanFactory.registerBeanDefinition("userToken",
	                BeanDefinitionBuilder.genericBeanDefinition(UserToken.class)
	                		.setScope("prototype")
	                        .addConstructorArgValue(jwt.getClaim("userName").asString()) //name
	                        .addConstructorArgValue(jwt.getClaim("login").asString()) //login
	                        .addConstructorArgValue(jwt.getClaim("userId").asString()) //userId
	                        .getBeanDefinition()
	        );
		}

		return chain.filter(exchange);
	}

}
