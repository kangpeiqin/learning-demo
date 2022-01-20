package com.example.distributedLock.idempotent.exception;

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
