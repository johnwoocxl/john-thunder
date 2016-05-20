package com.nepxion.thunder.common.serialization.json;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nepxion.thunder.common.constant.ThunderConstants;
import com.nepxion.thunder.common.serialization.SerializerException;

public class AliSerializer {
    public static String toJson(Object object) {
        if (object == null) {
            throw new SerializerException("Object is null");
        }

        return JSON.toJSONStringWithDateFormat(object, ThunderConstants.DATE_FORMAT, SerializerFeature.WriteClassName);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            throw new SerializerException("Json is null or empty");
        }

        return JSON.parseObject(json, clazz);
    }

    /**
     * return the byte array of the string 
     * which chenged from the java bean such
     * as User
     * serialize
     * @param obj
     * @return
     *        byte[]
     * @author wb-wj190071
     * @date 2016年4月12日 下午5:05:21
     * @exception
     * @since  1.0.0
     */
    public static byte[] serialize(Object obj){
        return toJson(obj).getBytes();
    }
    
    /**
     * deserialize the given byte array to Object
     * deserialize
     * @param strArray
     *        void
     * @author wb-wj190071
     * @date 2016年4月12日 下午5:08:43
     * @exception
     * @since  1.0.0
     */
    public static void deserialize(byte[] strArray){
        fromJson(new String(strArray));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json) {
        if (StringUtils.isEmpty(json)) {
            throw new SerializerException("Json is null or empty");
        }

        return (T) JSON.parse(json);
    }

    public static JSONObject parseJson(String json) {
        if (StringUtils.isEmpty(json)) {
            throw new SerializerException("Json is null or empty");
        }

        return JSON.parseObject(json);
    }
}