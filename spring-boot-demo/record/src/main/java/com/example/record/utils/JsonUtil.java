package com.example.record.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kpq
 * @since 1.0.0
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            log.error("Json转换异常，异常原因：{}", e.getMessage());
            throw new RuntimeException("Json转换异常");
        }
    }

    public static String toJson(Object entity) {
        try {
            return OBJECT_MAPPER.writeValueAsString(entity);
        } catch (Exception e) {
            log.error("Json转换异常，异常原因：{}", e.getMessage());
            throw new RuntimeException("Json转换异常");
        }
    }

}
