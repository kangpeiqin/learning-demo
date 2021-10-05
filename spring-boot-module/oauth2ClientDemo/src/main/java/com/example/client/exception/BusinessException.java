package com.example.client.exception;

import lombok.Getter;

/**
 * @author kpq
 * @since 1.0.0
 */
public class BusinessException extends RuntimeException {

    @Getter
    private Object errData;

    public BusinessException(String message) {
        super();
    }

    public BusinessException(String message, Object errData) {
        super();
        this.errData = errData;
    }

}
