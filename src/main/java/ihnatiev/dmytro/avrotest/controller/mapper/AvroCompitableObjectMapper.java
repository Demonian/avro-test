package ihnatiev.dmytro.avrotest.controller.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.avro.AvroRuntimeException;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificRecordBase;
import tech.allegro.schema.json2avro.converter.AvroConversionException;
import tech.allegro.schema.json2avro.converter.JsonAvroConverter;

import java.io.*;
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
        if (valueType.isTypeOrSubTypeOf(SpecificRecordBase.class)) {
            String jsonData = p0.readValueAsTree().toString();
            String modifiedAndValidatedString = validateJsonToAvroSchemaAndGet(jsonData, valueType);
            p0 = getFactory().createParser(modifiedAndValidatedString);
        }
        return super._readMapAndClose(p0, valueType);
    }

    private String validateJsonToAvroSchemaAndGet(String inputMessage, JavaType valueType) throws IOException {
        try {
            Method getClassSchema = valueType.getRawClass().getMethod("getClassSchema");
            Schema schema = (Schema) getClassSchema.invoke(null);
            JsonAvroConverter converter = new JsonAvroConverter();
            // conversion to binary Avro
            GenericData.Record record = converter.convertToGenericDataRecord(inputMessage.getBytes(), schema);
            return record.toString();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new AvroRuntimeException("Generated class from avro schema was modified or broken" + valueType.toString(), e);
        } catch (AvroConversionException a) {
            //need to handle erro with status 422 Unprocessable Entity
            throw new AvroTypeException(a.getCause().getMessage());
        }
    }
}
