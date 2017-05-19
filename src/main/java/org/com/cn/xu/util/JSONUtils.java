package org.com.cn.xu.util;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;

public class JSONUtils {
    private static ObjectMapper mapper;

    private static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
            //设置为中国上海时区  
            mapper.setTimeZone(TimeZone.getTimeZone("GMT+08"));
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return mapper;
    }

    /**
     * json转换bean
     * 
     * @param json
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static <T> T toBean(String json, Class<T> beanClass) throws Exception {
        return getMapper().readValue(json, beanClass);
    }

    /**
     * json转换List
     * 
     * @param json
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static <T> T toBeanList(String json, Class<?> beanClass) throws Exception {
        JavaType javaType = getCollectionType(ArrayList.class, beanClass);
        return getMapper().readValue(json, javaType);
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?> elementClasses) {
        return getMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 对象转换成Json
     * 
     * @param value
     * @return
     * @throws JsonProcessingException
     */
    public static String toBeanString(Object value) throws JsonProcessingException {
        return getMapper().writeValueAsString(value);
    }

    /**
     * json转换成map
     * 
     * @param json
     * @param t
     * @return
     * @throws IOException
     */
    public static <T> T toBeanMap(String json, TypeReference<?> t) throws IOException {
        return getMapper().readValue(json, t);
    }

    /**
     * 解析返回的response,获取status和data
     * 
     * @param json
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static HashMap<String, Object> getParseResponse(String json) throws JsonParseException, JsonMappingException, IOException {
        return getMapper().readValue(json, new TypeReference<HashMap<String, Object>>() {});
    }

}
