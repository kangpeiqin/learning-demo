package com.example.mall.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author KPQ
 * @date 2022/1/24
 */
@Data
@AllArgsConstructor
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    @SuppressWarnings("unchecked")
    public static <T> Result<T> ok(T data) {
        return new Result(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> Result<T> ok(String msg, T data) {
        return new Result(HttpStatus.OK.value(), msg, data);
    }

    @SuppressWarnings("unchecked")
    public static <T> Result<T> error(String msg) {
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                msg, null);
    }
}
