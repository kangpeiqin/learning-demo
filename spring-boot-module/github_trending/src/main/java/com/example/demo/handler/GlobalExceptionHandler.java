package com.example.demo.handler;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * @author KPQ
 * @date 2021/11/22
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handleBaseException(RuntimeException ex) {
        log.error("occur BaseException:{}", ex.getMessage());
        Map<String, String> map = Maps.newHashMap();
        map.put("msg", ex.getMessage());
        return map;
    }

}
