package tech.allegro.schema.json2avro.converter;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.avro.AvroRuntimeException;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecordBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.allegro.schema.json2avro.converter.AvroConversionException;
import tech.allegro.schema.json2avro.converter.JsonGenericRecordReader;

import static tech.allegro.schema.json2avro.converter.AvroTypeExceptions.*;

public class JsonGenericRecordReaderV2 extends JsonGenericRecordReader {
    private static final Logger LOG = LoggerFactory.getLogger(JsonGenericRecordReaderV2.class);
    
    private static final Object INCOMPATIBLE = new Object();
    
    @Override
    public GenericData.Record read(Map<String,Object> json, Schema schema) {
        Deque<String> path = new ArrayDeque<>();
        try {
            return readRecord(json, schema, path);
        } catch (AvroRuntimeException ex) {
            throw new AvroConversionException("Failed to convert JSON to Avro", ex);
        }
    }
    
    private GenericData.Record readRecord(Map<String,Object> json, Schema schema, Deque<String> path) {
        Set<String> errors = new HashSet<>();
        
        GenericRecordBuilder record = new GenericRecordBuilder(schema);
        json.entrySet().forEach(entry ->
                Optional.ofNullable(schema.getField(entry.getKey()))
                        .ifPresent(field -> readSetAndCaptureError(record, field, entry, path, errors)));
        
        if (!errors.isEmpty()) {
            throw new AvroValidationException("Validation failed", errors);
        }
        
        return record.build();
    }
    
    private void readSetAndCaptureError(GenericRecordBuilder record, Schema.Field field, Map.Entry<String, Object> entry, 
            Deque<String> path, Set<String> errors) {
        try {
            record.set(field, read(field, field.schema(), entry.getValue(), path, false));
        } catch (AvroTypeException e) {
            LOG.warn(e.getMessage());
            errors.add(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private Object read(Schema.Field field, Schema schema, Object value, Deque<String> path, boolean silently) {
        boolean pushed = !field.name().equals(path.peek());
        if(pushed) {
            path.push(field.name());
        }
        Object result;

        switch (schema.getType()) {
            case RECORD:  result = onValidType(value, Map.class, path, silently, map -> readRecord(map, schema, path)); break;
            case ARRAY:   result = onValidType(value, List.class, path, silently, list -> readArray(field, schema, list, path)); break;
            case MAP:     result = onValidType(value, Map.class, path, silently, map -> readMap(field, schema, map, path)); break;
            case UNION:   result = readUnion(field, schema, value, path); break;
            case INT:     result = onValidNumber(value, path, silently, Number::intValue); break;
            case LONG:    result = onValidNumber(value, path, silently, Number::longValue); break;
            case FLOAT:   result = onValidNumber(value, path, silently, Number::floatValue); break;
            case DOUBLE:  result = onValidNumber(value, path, silently, Number::doubleValue); break;
            case BOOLEAN: result = onValidType(value, Boolean.class, path, silently, bool -> bool); break;
            case ENUM:    result = onValidType(value, String.class, path, silently, string -> ensureEnum(schema, string, path)); break;
            case STRING:  result = onValidType(value, String.class, path, silently, string -> string); break;
            case NULL:    result = value == null ? value : INCOMPATIBLE; break;
            default: throw new AvroTypeException("Unsupported type: " + field.schema().getType());
        }

        if(pushed) {
            path.pop();
        }
        return result;
    }
    
    private List<Object> readArray(Schema.Field field, Schema schema, List<Object> items, Deque<String> path) {
        return items.stream().map(item -> read(field, schema.getElementType(), item, path, false)).collect(Collectors.toList());
    }

    private Map<String, Object> readMap(Schema.Field field, Schema schema, Map<String, Object> map, Deque<String> path) {
        Map<String, Object> result = new HashMap<>(map.size());
        map.forEach((k, v) -> result.put(k, read(field, schema.getValueType(), v, path, false)));
        return result;
    }
    
    private Object readUnion(Schema.Field field, Schema schema, Object value, Deque<String> path) {
        List<Schema> types = schema.getTypes();
        for (Schema type : types) {
            try {
                Object nestedValue = read(field, type, value, path, true);
                if (nestedValue == INCOMPATIBLE) {
                    continue;
                } else {
                    return nestedValue;
                }
            } catch (AvroRuntimeException e) {
                // thrown only for union of more complex types like records
                continue;
            }
        }
        throw unionException(
                field.name(),
                types.stream().map(Schema::getType).map(Object::toString).collect(Collectors.joining(", ")),
                path);
    }

    private Object ensureEnum(Schema schema, Object value, Deque<String> path) {
        List<String> symbols = schema.getEnumSymbols();
        if(symbols.contains(value)){
           return new GenericData.EnumSymbol(schema, value);
        }
        throw enumException(path, symbols.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    }

    public static class AvroValidationException extends AvroTypeException {
        private static final long serialVersionUID = 1L;
        
        private Collection<String> errors;
        
        public AvroValidationException(String message, Collection<String> errors) {
            super(message);
            this.errors = errors;
        }
        
        @Override
        public String getMessage() {
            return super.getMessage() + " >>> [" + String.join("], [", errors) + "]";
        }
        
    }
    
}
