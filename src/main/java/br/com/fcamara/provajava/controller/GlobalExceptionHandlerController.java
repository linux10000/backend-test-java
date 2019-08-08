package br.com.fcamara.provajava.controller;


import java.util.HashMap;
import java.util.Map;

//import org.postgresql.util.PSQLException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import com.auth0.jwt.exceptions.TokenExpiredException;

import br.com.fcamara.provajava.helper.Utils;
//import br.com.hcf.utils.exception.MensagemBancoDadosException;
//import br.com.hcf.utils.exception.RegistroAlteradoPorOutroUsuarioException;
//import br.com.hcf.utils.funcoes.Conversao;
import reactor.core.publisher.Mono;


@Component
@Order(-2)
public class GlobalExceptionHandlerController implements WebExceptionHandler {
	
	
	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		ex.printStackTrace();
		
//		if ( ex instanceof JpaSystemException 
//				|| ex instanceof UncategorizedSQLException 
//				)
//			ex = handleJpaException(ex);


		exchange.getResponse().getHeaders().set("Content-Type", "application/json");
		exchange.getResponse().getHeaders().set("Access-Control-Allow-Origin", "*");
		exchange.getResponse().getHeaders().set("Vary", "Origin, Access-Control-Request-Method, Access-Control-Request-Headers");
		
		Map<String,String> params = new HashMap<String, String>();

		try {
			if ( ex instanceof TokenExpiredException  ) {
				params.put("message", ex.getMessage());
				params.put("exception", ex.getClass().getName());
				
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().writeWith(Utils.mapToBinaryFlux(exchange, params));
			}
			
//			if ( ex instanceof RegistroAlteradoPorOutroUsuarioException ) {
//				params.put("message", ex.getMessage());
//				params.put("exception", ex.getClass().getName());
//				params.put("code", "001");
//				
//				exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
//				return exchange.getResponse().writeWith(Conversao.mapToBinaryFlux(exchange, params));
//			}
//			
//			if ( ex instanceof MensagemBancoDadosException ) {
//				params.put("message", ex.getMessage());
//				params.put("exception", ex.getClass().getName());
//				params.put("code", "002");
//				
//				exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
//				return exchange.getResponse().writeWith(Conversao.mapToBinaryFlux(exchange, params));
//			}
			
			params.put("message", ex.getMessage());
			params.put("exception", ex.getClass().getName());
			
			exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			return exchange.getResponse().writeWith(Utils.mapToBinaryFlux(exchange, params));
		} catch (Exception e) {
			e.printStackTrace();
			return exchange.getResponse().setComplete();
		}
	}
	
//	private Throwable handleJpaException(Throwable ex) {
//		//if ( ex.getCause() instanceof GenericJDBCException ) {
//		PSQLException e = getExceptionOnStack(ex, PSQLException.class, 10, 0); 
//		if ( e == null )
//			return ex;
//		
//		if ( e != null ) {
//			if ( e.getMessage().toLowerCase().contains("registro alterado") && e.getMessage().toLowerCase().contains("fn_ts()") )
//				return new RegistroAlteradoPorOutroUsuarioException();
//			else if ( e.getMessage().toLowerCase().contains(".---") )
//				return new MensagemBancoDadosException(e.getMessage().substring(11, e.getMessage().indexOf("---.", 11)));
//		}		
//			
//		//}
//		
//		return ex;
//	}
	
//	@SuppressWarnings("unchecked")
//	private <T> T getExceptionOnStack(Throwable throwable, Class<T> throwableClass, int maxLevel, int currentLevel) {
//		if ( throwable == null || currentLevel > maxLevel )
//			return null;
//		
//		if ( throwable.getClass() == throwableClass )
//			return (T) throwable;
//		
//		return getExceptionOnStack(throwable.getCause(), throwableClass, maxLevel, currentLevel + 1);
//	}
}
