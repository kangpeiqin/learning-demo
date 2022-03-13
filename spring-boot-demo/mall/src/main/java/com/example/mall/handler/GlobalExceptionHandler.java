package com.example.mall.handler;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author KPQ
 * @date 2022/1/24
 */
//@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, String> handleBaseException(RuntimeException ex) {
        log.error("occur BaseException:{}", ex.getMessage());
        Map<String, String> map = Maps.newHashMap();
        map.put("msg", ex.getMessage());
        return map;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("occur BaseException:{}", ex.getMessage());
        Map<String, String> map = Maps.newHashMap();
        map.put("msg", ex.getMessage());
        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, String> handleException(Exception ex) {
        log.error("occur BaseException:{}", ex.getMessage());
        Map<String, String> map = Maps.newHashMap();
        map.put("msg", ex.getMessage());
        return map;
    }

}
