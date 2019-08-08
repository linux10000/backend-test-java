package br.com.fcamara.provajava.helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration
@EntityScan("br.com.fcamara.provajava.pojo")
@EnableJpaRepositories(basePackages = "br.com.fcamara.provajava.dao")
@ImportResource({"classpath*:applicationContext.xml"})
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}

@Configuration
@EnableWebFlux
class CorsGlobalConfiguration implements WebFluxConfigurer {
 
	private ObjectMapper objectMapper = Utils.getObjectMapper();
	
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
          .allowedOrigins("*")
          .allowedMethods("*")
//          .allowedMethods("GET, POST, PUT, DELETE, OPTIONS")
          .maxAge(3600);
    }
    
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
    	configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
    	configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
    }
    
    @Bean
    ObjectMapper objectMapper() {
    	return objectMapper;
    }
}
