package ihnatiev.dmytro.avrotest.controller.mapper;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.http.HttpInputMessage;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Dmytro Ihnatiev.
 * @version 1.0
 * @since 26.07.2018
 */
public class AvroCompitableObjectMapper extends ObjectMapper {

    @Override
    protected Object _readMapAndClose(JsonParser p0, JavaType valueType) throws IOException {
        String jsonData = p0.readValueAsTree().toString();
        validateJsonToAvroSchema(jsonData, valueType);
        return super._readMapAndClose(p0, valueType);
    }

    private void validateJsonToAvroSchema(String inputMessage, JavaType valueType) throws IOException {
        if (valueType.isTypeOrSubTypeOf(SpecificRecordBase.class)) {
            try {
                Method getClassSchema = valueType.getRawClass().getMethod("getClassSchema");
                Schema schema = (Schema) getClassSchema.invoke(null);
                DatumReader reader = new GenericDatumReader(schema);
                Decoder decoder = DecoderFactory.get().jsonDecoder(schema, inputMessage);
                reader.read(null, decoder);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
