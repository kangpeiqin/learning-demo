package com.example.distributedlock.idempotent.exception;

import com.example.distributedlock.idempotent.exception.BaseIdempotentException;

/**
 * @author KPQ
 * @date 2021/12/23
 */
public class InvokeException extends BaseIdempotentException {

    public InvokeException() {
        super();
    }

    public InvokeException(String message) {
        super(message);
    }

    public InvokeException(Throwable cause) {
        super(cause);
    }
}
