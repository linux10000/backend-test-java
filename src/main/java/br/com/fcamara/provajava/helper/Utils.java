package br.com.fcamara.provajava.helper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.base.Strings;

import reactor.core.publisher.Flux;

public class Utils {
	
	private static ObjectMapper objectMapper;

	public static ObjectMapper getObjectMapper(){
		if ( objectMapper == null ){
			objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false);
			objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			objectMapper.configure(SerializationFeature.EAGER_SERIALIZER_FETCH, false);
			objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			objectMapper.registerModule(new ParameterNamesModule());
			objectMapper.registerModule(new Jdk8Module());
			
			objectMapper.setSerializationInclusion(Include.NON_NULL);	
			
			JavaTimeModule javaTimeModule = new JavaTimeModule();
			javaTimeModule.addSerializer(OffsetDateTime.class, new JsonSerializer<OffsetDateTime>() {
				@Override
				public void serialize(OffsetDateTime value, JsonGenerator gen,
						SerializerProvider serializers) throws IOException {					
					gen.writeString(offsetDateTimeToString(value));
				}
			});
			
			javaTimeModule.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
				@Override
				public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt)
						throws IOException {
					return stringToOffsetDateTime(p.getValueAsString(), true);
				}
			});
			
			javaTimeModule.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
				@Override
				public void serialize(LocalDate value, JsonGenerator gen,
						SerializerProvider serializers) throws IOException {					
					gen.writeString(localDateToString(value));
				}
			});
			
			javaTimeModule.addDeserializer(LocalDate.class, new JsonDeserializer<LocalDate>() {
				@Override
				public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
						throws IOException {
					return stringToLocalDate(p.getValueAsString(), true);
				}
			});
			
			objectMapper.registerModule(javaTimeModule);
			
		}
		
		return objectMapper;
	}
	
	public static String offsetDateTimeToString(OffsetDateTime obj){
		if ( obj == null )
			return null;
		return obj.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ"));
	}
	
	public static OffsetDateTime stringToOffsetDateTime(String obj, boolean throwException){
		if ( Strings.isNullOrEmpty(obj) )
			return null;
		
		OffsetDateTime r;
		try {
			r = OffsetDateTime.parse(obj, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ"));
		} catch (Exception e) {
			if ( throwException )
				throw e;
			else r = null;
		}
		
		return r;
	}
	
	public static String localDateToString(LocalDate obj){
		if ( obj == null )
			obj = LocalDate.of(1980, 1, 1);
		return obj.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public static LocalDate stringToLocalDate(String obj, boolean throwException){
		if ( Strings.isNullOrEmpty(obj) )
			return LocalDate.of(1980, 1, 1);
		
		LocalDate r;
		try {
			r = LocalDate.parse(obj, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		} catch (Exception e) {
			if ( throwException )
				throw e;
			else r = null;
		}
		
		return r;
	}
	
	public static Flux<DataBuffer> mapToBinaryFlux(ServerWebExchange exchange, Map<String, String> map) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(om.writeValueAsBytes(map));
		return Flux.just(buffer);
	}
}
