package com.example.distributedlock.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity(new Result<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data), HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    public static <T> ResponseEntity<T> error(String msg) {
        return new ResponseEntity(new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                msg, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
