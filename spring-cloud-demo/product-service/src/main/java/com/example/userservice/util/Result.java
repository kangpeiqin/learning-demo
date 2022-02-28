package com.example.userservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


/**
 * @author KPQ
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class Result<T> {

    private int code;

    private String msg;

    private T data;

    public static <T> Result success(T data) {
        Result<T> result = new Result<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
        return result;
    }

    public static <T> Result error(String msg) {
        Result<T> result = new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
        return result;
    }

}
