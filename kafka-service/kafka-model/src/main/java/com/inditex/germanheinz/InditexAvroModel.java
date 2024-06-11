/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.inditex.germanheinz;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class InditexAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7012973544331673788L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"InditexAvroModel\",\"namespace\":\"com.inditex.germanheinz.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"productId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"brand\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"priority\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.UUIDConversion());
  }

  private static final BinaryMessageEncoder<InditexAvroModel> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<InditexAvroModel> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<InditexAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<InditexAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<InditexAvroModel> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this InditexAvroModel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a InditexAvroModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a InditexAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static InditexAvroModel fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.util.UUID id;
  private java.lang.String productId;
  private java.lang.String brand;
  private java.lang.String priority;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public InditexAvroModel() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param productId The new value for productId
   * @param brand The new value for brand
   * @param priority The new value for priority
   */
  public InditexAvroModel(java.util.UUID id, java.lang.String productId, java.lang.String brand, java.lang.String priority) {
    this.id = id;
    this.productId = productId;
    this.brand = brand;
    this.priority = priority;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return productId;
    case 2: return brand;
    case 3: return priority;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      new org.apache.avro.Conversions.UUIDConversion(),
      null,
      null,
      null,
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.util.UUID)value$; break;
    case 1: productId = value$ != null ? value$.toString() : null; break;
    case 2: brand = value$ != null ? value$.toString() : null; break;
    case 3: priority = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.util.UUID getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.util.UUID value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'productId' field.
   * @return The value of the 'productId' field.
   */
  public java.lang.String getProductId() {
    return productId;
  }


  /**
   * Sets the value of the 'productId' field.
   * @param value the value to set.
   */
  public void setProductId(java.lang.String value) {
    this.productId = value;
  }

  /**
   * Gets the value of the 'brand' field.
   * @return The value of the 'brand' field.
   */
  public java.lang.String getBrand() {
    return brand;
  }


  /**
   * Sets the value of the 'brand' field.
   * @param value the value to set.
   */
  public void setBrand(java.lang.String value) {
    this.brand = value;
  }

  /**
   * Gets the value of the 'priority' field.
   * @return The value of the 'priority' field.
   */
  public java.lang.String getPriority() {
    return priority;
  }


  /**
   * Sets the value of the 'priority' field.
   * @param value the value to set.
   */
  public void setPriority(java.lang.String value) {
    this.priority = value;
  }

  /**
   * Creates a new InditexAvroModel RecordBuilder.
   * @return A new InditexAvroModel RecordBuilder
   */
  public static InditexAvroModel.Builder newBuilder() {
    return new InditexAvroModel.Builder();
  }

  /**
   * Creates a new InditexAvroModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new InditexAvroModel RecordBuilder
   */
  public static InditexAvroModel.Builder newBuilder(InditexAvroModel.Builder other) {
    if (other == null) {
      return new InditexAvroModel.Builder();
    } else {
      return new InditexAvroModel.Builder(other);
    }
  }

  /**
   * Creates a new InditexAvroModel RecordBuilder by copying an existing InditexAvroModel instance.
   * @param other The existing instance to copy.
   * @return A new InditexAvroModel RecordBuilder
   */
  public static InditexAvroModel.Builder newBuilder(InditexAvroModel other) {
    if (other == null) {
      return new InditexAvroModel.Builder();
    } else {
      return new InditexAvroModel.Builder(other);
    }
  }

  /**
   * RecordBuilder for InditexAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<InditexAvroModel>
    implements org.apache.avro.data.RecordBuilder<InditexAvroModel> {

    private java.util.UUID id;
    private java.lang.String productId;
    private java.lang.String brand;
    private java.lang.String priority;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(InditexAvroModel.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.productId)) {
        this.productId = data().deepCopy(fields()[1].schema(), other.productId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.brand)) {
        this.brand = data().deepCopy(fields()[2].schema(), other.brand);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.priority)) {
        this.priority = data().deepCopy(fields()[3].schema(), other.priority);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing InditexAvroModel instance
     * @param other The existing instance to copy.
     */
    private Builder(InditexAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.productId)) {
        this.productId = data().deepCopy(fields()[1].schema(), other.productId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.brand)) {
        this.brand = data().deepCopy(fields()[2].schema(), other.brand);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.priority)) {
        this.priority = data().deepCopy(fields()[3].schema(), other.priority);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.util.UUID getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public InditexAvroModel.Builder setId(java.util.UUID value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public InditexAvroModel.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'productId' field.
      * @return The value.
      */
    public java.lang.String getProductId() {
      return productId;
    }


    /**
      * Sets the value of the 'productId' field.
      * @param value The value of 'productId'.
      * @return This builder.
      */
    public InditexAvroModel.Builder setProductId(java.lang.String value) {
      validate(fields()[1], value);
      this.productId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'productId' field has been set.
      * @return True if the 'productId' field has been set, false otherwise.
      */
    public boolean hasProductId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'productId' field.
      * @return This builder.
      */
    public InditexAvroModel.Builder clearProductId() {
      productId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'brand' field.
      * @return The value.
      */
    public java.lang.String getBrand() {
      return brand;
    }


    /**
      * Sets the value of the 'brand' field.
      * @param value The value of 'brand'.
      * @return This builder.
      */
    public InditexAvroModel.Builder setBrand(java.lang.String value) {
      validate(fields()[2], value);
      this.brand = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'brand' field has been set.
      * @return True if the 'brand' field has been set, false otherwise.
      */
    public boolean hasBrand() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'brand' field.
      * @return This builder.
      */
    public InditexAvroModel.Builder clearBrand() {
      brand = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'priority' field.
      * @return The value.
      */
    public java.lang.String getPriority() {
      return priority;
    }


    /**
      * Sets the value of the 'priority' field.
      * @param value The value of 'priority'.
      * @return This builder.
      */
    public InditexAvroModel.Builder setPriority(java.lang.String value) {
      validate(fields()[3], value);
      this.priority = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'priority' field has been set.
      * @return True if the 'priority' field has been set, false otherwise.
      */
    public boolean hasPriority() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'priority' field.
      * @return This builder.
      */
    public InditexAvroModel.Builder clearPriority() {
      priority = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public InditexAvroModel build() {
      try {
        InditexAvroModel record = new InditexAvroModel();
        record.id = fieldSetFlags()[0] ? this.id : (java.util.UUID) defaultValue(fields()[0]);
        record.productId = fieldSetFlags()[1] ? this.productId : (java.lang.String) defaultValue(fields()[1]);
        record.brand = fieldSetFlags()[2] ? this.brand : (java.lang.String) defaultValue(fields()[2]);
        record.priority = fieldSetFlags()[3] ? this.priority : (java.lang.String) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<InditexAvroModel>
    WRITER$ = (org.apache.avro.io.DatumWriter<InditexAvroModel>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<InditexAvroModel>
    READER$ = (org.apache.avro.io.DatumReader<InditexAvroModel>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}









