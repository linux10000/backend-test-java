package br.com.fcamara.provajava.helper;

import java.nio.charset.StandardCharsets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.AbstractJackson2Encoder;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.MimeType;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
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
          .maxAge(3600);
    }
    
	private static final MimeType[] DEFAULT_XML_MIME_TYPES = new MimeType[] {
			new MimeType("application", "xml", StandardCharsets.UTF_8)
		};
	
//	@Lazy(true)
//	@Autowired
//	private XmlStringReader stringReader;
    
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
    	configurer.registerDefaults(true);
    	
    	configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
    	configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
//        configurer.customCodecs().decoder( Jaxb2XmlDecoder());  
//        configurer.customCodecs().encoder(new Jaxb2XmlEncoder());
//        configurer.defaultCodecs().jaxb2Decoder(new Jaxb2XmlDecoder());  
//        configurer.defaultCodecs().jaxb2Encoder(new Jaxb2XmlEncoder());
    	
    	
//    	configurer.customCodecs().encoder(new Jackson2JsonEncoder(objectMapper));
//    	configurer.customCodecs().decoder(new Jackson2JsonDecoder(objectMapper));
    	
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.xml().build();
//		configurer.customCodecs().reader(stringReader);
		configurer.customCodecs().encoder(new AbstractJackson2Encoder(objectMapper, DEFAULT_XML_MIME_TYPES) {});
    	
    	
    }
    
    @Bean
    ObjectMapper objectMapper() {
    	return objectMapper;
    }
}
