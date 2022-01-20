package com.example.distributedLock.idempotent.service.impl;

import com.example.distributedLock.idempotent.exception.BaseIdempotentException;

/**
 * @author KPQ
 * @date 2021/12/23
 */
public class RepeatedSubmitException extends BaseIdempotentException {

    public RepeatedSubmitException() {
        super();
    }

    public RepeatedSubmitException(String message) {
        super(message);
    }

}