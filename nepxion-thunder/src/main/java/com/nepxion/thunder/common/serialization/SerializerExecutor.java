package com.nepxion.thunder.common.serialization;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nepxion.thunder.common.compression.CompressorExecutor;
import com.nepxion.thunder.common.compression.CompressorFactory;
import com.nepxion.thunder.common.entity.CompressorType;
import com.nepxion.thunder.common.entity.SerializerType;
import com.nepxion.thunder.common.serialization.binary.FSTSerializer;
import com.nepxion.thunder.common.serialization.binary.JDKSerializer;
import com.nepxion.thunder.common.serialization.binary.KryoSerializer;
import com.nepxion.thunder.common.serialization.json.AliSerializer;
import com.nepxion.thunder.common.serialization.json.FSTJsonSerializer;
import com.nepxion.thunder.common.serialization.json.JacksonSerializer;

public class SerializerExecutor {
    private static final Logger LOG = LoggerFactory.getLogger(SerializerExecutor.class);
    
    public static byte[] serialize(Serializable object) {
        return serialize(object, CompressorFactory.isCompress());
    }

    public static <T> T deserialize(byte[] bytes) {
        return deserialize(bytes, CompressorFactory.isCompress());
    }
    
    public static byte[] serialize(Serializable object, boolean compress) {
        return serialize(object, SerializerFactory.getBinarySerializerType(), CompressorFactory.getCompressorType(), compress, SerializerFactory.isSerializerLogPrint());
    }

    public static <T> T deserialize(byte[] bytes, boolean compress) {
        return deserialize(bytes, SerializerFactory.getBinarySerializerType(), CompressorFactory.getCompressorType(), compress, SerializerFactory.isSerializerLogPrint());
    }

    public static String toJson(Object object) {
        return toJson(object, SerializerFactory.getJsonSerializerType());
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return fromJson(json, clazz, SerializerFactory.getJsonSerializerType());
    }

    public static byte[] serialize(Serializable object, SerializerType serializerType, CompressorType compressorType, boolean compress, boolean serializerLogPrint) {
        byte[] bytes = null;
        if (serializerType == SerializerType.FST_BINARY) {
            bytes = FSTSerializer.serialize(object);
        } else if (serializerType == SerializerType.KRYO_BINARY) {
            bytes = KryoSerializer.serialize(object);
        } else if (serializerType == SerializerType.JDK_BINARY) {
            bytes = JDKSerializer.serialize(object);
        } else if (serializerType == SerializerType.ALI_JSON) {
            bytes = AliSerializer.serialize(object);
        } else {
            throw new SerializerException("Invalid serializer type of binary : " + serializerType);
        }
        
        if (compress) {
            print(bytes, null, true, false, serializerLogPrint, serializerType);
            
            bytes = compress(bytes, compressorType);
            
            print(bytes, null, true, true, serializerLogPrint, serializerType);
        } else {
            print(bytes, object.getClass(), true, false, serializerLogPrint, serializerType);
        }

        return bytes;
    }

    public static <T> T deserialize(byte[] bytes, SerializerType serializerType, CompressorType compressorType, boolean compress, boolean serializerLogPrint) {
        if (compress) {    
            print(bytes, null, false, true, serializerLogPrint, serializerType);
            
            bytes = decompress(bytes, compressorType);
            
            print(bytes, null, false, false, serializerLogPrint, serializerType);
        }
                
        T object = null;
        if (serializerType == SerializerType.FST_BINARY) {
            object = FSTSerializer.deserialize(bytes);
        } else if (serializerType == SerializerType.KRYO_BINARY) {
            object = KryoSerializer.deserialize(bytes);
        } else if (serializerType == SerializerType.JDK_BINARY) {
            object = JDKSerializer.deserialize(bytes);
        } else {
            throw new SerializerException("Invalid serializer type of binary : " + serializerType);
        }
        
        if (!compress) {
            print(bytes, object.getClass(), false, false, serializerLogPrint, serializerType);
        }
        
        return object;
    }

    public static String toJson(Object object, SerializerType serializerType) {
        if (serializerType == SerializerType.JACKSON_JSON) {
            return JacksonSerializer.toJson(object);
        } else if (serializerType == SerializerType.ALI_JSON) {
            return AliSerializer.toJson(object);
        } else if (serializerType == SerializerType.FST_JSON) {
            return FSTJsonSerializer.toJson(object);
        } else {
            throw new SerializerException("Invalid serializer type of json : " + serializerType);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz, SerializerType serializerType) {
        if (serializerType == SerializerType.JACKSON_JSON) {
            return JacksonSerializer.fromJson(json, clazz);
        } else if (serializerType == SerializerType.ALI_JSON) {
            return AliSerializer.fromJson(json, clazz);
        } else if (serializerType == SerializerType.FST_JSON) {
            return FSTJsonSerializer.fromJson(json, clazz);
        } else {
            throw new SerializerException("Invalid serializer type of json : " + serializerType);
        }
    }
    
    public static byte[] compress(byte[] bytes, CompressorType compressorType) {
        return CompressorExecutor.compress(bytes, compressorType);
    }

    public static byte[] decompress(byte[] bytes, CompressorType compressorType) {
        return CompressorExecutor.decompress(bytes, compressorType);
    }
    
    public static void print(byte[] bytes, Class<?> clazz, boolean serialize, boolean compress, boolean serializerLogPrint, SerializerType serializerType) {
        if (serializerLogPrint) {
            if (clazz != null) {
                LOG.info("{}, size={} KB, {} Byte, compress={}, class={}", serialize ? "Serialize" : "Deserialize", (float) bytes.length / 1024, bytes.length, compress, clazz.getName());
            } else {
                if(compress){
                    LOG.info("{} by {}, after compressed by facebook QuickLz the size={} KB, {} Byte, compress={}", serialize ? "Serialize" : "Deserialize", serializerType.getValue(), (float) bytes.length / 1024, bytes.length, compress);
                } else {
                    LOG.info("{} by {}, before compress by facebook QuickLz  the size={} KB, {} Byte, compress={}", serialize ? "Serialize" : "Deserialize", serializerType.getValue(), (float) bytes.length / 1024, bytes.length, compress);
                }
                
            }
        }
    }
}