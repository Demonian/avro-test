/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package ihnatiev.dmytro.avrotest.model.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Hello extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -1879898437168596793L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Hello\",\"namespace\":\"ihnatiev.dmytro.avrotest.model.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"long\",\"doc\":\"id of the object\"},{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"optional name field with default value\",\"default\":\"default\"},{\"name\":\"surname\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"optional name field with default value\",\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Hello> ENCODER =
      new BinaryMessageEncoder<Hello>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Hello> DECODER =
      new BinaryMessageDecoder<Hello>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Hello> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Hello> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Hello>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Hello to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Hello from a ByteBuffer. */
  public static Hello fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** id of the object */
   private long id;
  /** optional name field with default value */
   private java.lang.String name;
  /** optional name field with default value */
   private java.lang.String surname;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Hello() {}

  /**
   * All-args constructor.
   * @param id id of the object
   * @param name optional name field with default value
   * @param surname optional name field with default value
   */
  public Hello(java.lang.Long id, java.lang.String name, java.lang.String surname) {
    this.id = id;
    this.name = name;
    this.surname = surname;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return name;
    case 2: return surname;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Long)value$; break;
    case 1: name = (java.lang.String)value$; break;
    case 2: surname = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return id of the object
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * id of the object
   * @param value the value to set.
   */
  public void setId(java.lang.Long value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'name' field.
   * @return optional name field with default value
   */
  public java.lang.String getName() {
    return name;
  }

  /**
   * Sets the value of the 'name' field.
   * optional name field with default value
   * @param value the value to set.
   */
  public void setName(java.lang.String value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'surname' field.
   * @return optional name field with default value
   */
  public java.lang.String getSurname() {
    return surname;
  }

  /**
   * Sets the value of the 'surname' field.
   * optional name field with default value
   * @param value the value to set.
   */
  public void setSurname(java.lang.String value) {
    this.surname = value;
  }

  /**
   * Creates a new Hello RecordBuilder.
   * @return A new Hello RecordBuilder
   */
  public static ihnatiev.dmytro.avrotest.model.avro.Hello.Builder newBuilder() {
    return new ihnatiev.dmytro.avrotest.model.avro.Hello.Builder();
  }

  /**
   * Creates a new Hello RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Hello RecordBuilder
   */
  public static ihnatiev.dmytro.avrotest.model.avro.Hello.Builder newBuilder(ihnatiev.dmytro.avrotest.model.avro.Hello.Builder other) {
    return new ihnatiev.dmytro.avrotest.model.avro.Hello.Builder(other);
  }

  /**
   * Creates a new Hello RecordBuilder by copying an existing Hello instance.
   * @param other The existing instance to copy.
   * @return A new Hello RecordBuilder
   */
  public static ihnatiev.dmytro.avrotest.model.avro.Hello.Builder newBuilder(ihnatiev.dmytro.avrotest.model.avro.Hello other) {
    return new ihnatiev.dmytro.avrotest.model.avro.Hello.Builder(other);
  }

  /**
   * RecordBuilder for Hello instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Hello>
    implements org.apache.avro.data.RecordBuilder<Hello> {

    /** id of the object */
    private long id;
    /** optional name field with default value */
    private java.lang.String name;
    /** optional name field with default value */
    private java.lang.String surname;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(ihnatiev.dmytro.avrotest.model.avro.Hello.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.surname)) {
        this.surname = data().deepCopy(fields()[2].schema(), other.surname);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Hello instance
     * @param other The existing instance to copy.
     */
    private Builder(ihnatiev.dmytro.avrotest.model.avro.Hello other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.name)) {
        this.name = data().deepCopy(fields()[1].schema(), other.name);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.surname)) {
        this.surname = data().deepCopy(fields()[2].schema(), other.surname);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * id of the object
      * @return The value.
      */
    public java.lang.Long getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * id of the object
      * @param value The value of 'id'.
      * @return This builder.
      */
    public ihnatiev.dmytro.avrotest.model.avro.Hello.Builder setId(long value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * id of the object
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * id of the object
      * @return This builder.
      */
    public ihnatiev.dmytro.avrotest.model.avro.Hello.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'name' field.
      * optional name field with default value
      * @return The value.
      */
    public java.lang.String getName() {
      return name;
    }

    /**
      * Sets the value of the 'name' field.
      * optional name field with default value
      * @param value The value of 'name'.
      * @return This builder.
      */
    public ihnatiev.dmytro.avrotest.model.avro.Hello.Builder setName(java.lang.String value) {
      validate(fields()[1], value);
      this.name = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * optional name field with default value
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'name' field.
      * optional name field with default value
      * @return This builder.
      */
    public ihnatiev.dmytro.avrotest.model.avro.Hello.Builder clearName() {
      name = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'surname' field.
      * optional name field with default value
      * @return The value.
      */
    public java.lang.String getSurname() {
      return surname;
    }

    /**
      * Sets the value of the 'surname' field.
      * optional name field with default value
      * @param value The value of 'surname'.
      * @return This builder.
      */
    public ihnatiev.dmytro.avrotest.model.avro.Hello.Builder setSurname(java.lang.String value) {
      validate(fields()[2], value);
      this.surname = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'surname' field has been set.
      * optional name field with default value
      * @return True if the 'surname' field has been set, false otherwise.
      */
    public boolean hasSurname() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'surname' field.
      * optional name field with default value
      * @return This builder.
      */
    public ihnatiev.dmytro.avrotest.model.avro.Hello.Builder clearSurname() {
      surname = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Hello build() {
      try {
        Hello record = new Hello();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Long) defaultValue(fields()[0]);
        record.name = fieldSetFlags()[1] ? this.name : (java.lang.String) defaultValue(fields()[1]);
        record.surname = fieldSetFlags()[2] ? this.surname : (java.lang.String) defaultValue(fields()[2]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Hello>
    WRITER$ = (org.apache.avro.io.DatumWriter<Hello>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Hello>
    READER$ = (org.apache.avro.io.DatumReader<Hello>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
