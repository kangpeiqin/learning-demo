package com.example.seckill.handler;

import com.example.seckill.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author KPQ
 * @date 2022/1/24
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity handleBaseException(RuntimeException ex) {
        log.error("occur BaseException:{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("occur BaseException:{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(Exception ex) {
        log.error("occur BaseException:{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

}
