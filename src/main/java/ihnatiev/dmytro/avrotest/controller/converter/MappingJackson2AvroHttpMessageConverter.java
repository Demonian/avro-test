package ihnatiev.dmytro.avrotest.controller.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author Dmytro Ihnatiev.
 * @version 1.0
 * @since 26.07.2018
 */
public class MappingJackson2AvroHttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public MappingJackson2AvroHttpMessageConverter() {
        this(new Jackson2ObjectMapperBuilder().factory(new AvroFactory()).build());
    }

    public MappingJackson2AvroHttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper);
        Assert.isInstanceOf(AvroFactory.class, objectMapper.getFactory(), "AVROFactory required");
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return super.read(type, contextClass, inputMessage);
    }
}
