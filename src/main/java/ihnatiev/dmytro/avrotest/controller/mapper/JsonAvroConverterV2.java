package ihnatiev.dmytro.avrotest.controller.mapper;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;

import tech.allegro.schema.json2avro.converter.JsonAvroConverter;
import tech.allegro.schema.json2avro.converter.JsonGenericRecordReader;
import tech.allegro.schema.json2avro.converter.JsonGenericRecordReaderV2;

public class JsonAvroConverterV2 extends JsonAvroConverter {

    private JsonGenericRecordReader recordReader = new JsonGenericRecordReaderV2();
    
    @Override
    public GenericData.Record convertToGenericDataRecord(byte[] data, Schema schema) {
        return recordReader.read(data, schema);
    }
    
}
