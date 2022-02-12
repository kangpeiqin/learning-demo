package com.example.demo.security.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * @author KPQ
 * @date 2021/11/18
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class ResultUtil<T> {

    private ResultUtil() {
    }

    private int code;

    private String msg;

    private T data;


    public static <T> ResultUtil<T> error(String msg, T data) {
        ResultUtil<T> resultUtil = new ResultUtil<T>()
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMsg(msg)
                .setData(data);
        return resultUtil;
    }

    public static <T> ResultUtil<T> success(T data) {
        ResultUtil<T> resultUtil = new ResultUtil<T>()
                .setCode(HttpStatus.OK.value())
                .setMsg(HttpStatus.OK.getReasonPhrase())
                .setData(data);
        return resultUtil;
    }

    public static <T> ResultUtil<T> unauthorized() {
        return new ResultUtil<>(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), null);
    }

    public static <T> ResultUtil<T> forbidden() {
        return new ResultUtil<>(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), null);
    }

}
