package com.example.demo.exception;

import lombok.Getter;

/**
 * @author kpq
 * @since 1.0.0
 */
public class BusinessException extends RuntimeException {

    @Getter
    private Object errData;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Object errData) {
        super(message);
        this.errData = errData;
    }

}
