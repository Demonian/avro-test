package ihnatiev.dmytro.avrotest;

import com.fasterxml.jackson.databind.ObjectMapper;
import ihnatiev.dmytro.avrotest.controller.mapper.AvroCompitableObjectMapper;
import ihnatiev.dmytro.avrotest.model.avro.Hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AvroTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvroTestApplication.class, args);
	}


	@Configuration
	class Config {
//		@Bean
//		public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
//			return new MappingJackson2AvroHttpMessageConverter(objectMapper);
//		}

		@Bean
		@Primary
		public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
			AvroCompitableObjectMapper avroObjectMapper = new AvroCompitableObjectMapper();
			builder.configure(avroObjectMapper);
			return avroObjectMapper;
		}

	}

	@RestController
	class HelloController{

		@PostMapping(path = "/hello", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public String hello(@RequestBody Hello hello) {
			return "valid" + hello.getName();
		}
	}
}
