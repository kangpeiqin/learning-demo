package com.example.record.domain.entity;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author KPQ
 * @date 2022/2/16
 */
@Data
@ToString
public class Account implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String account;

    private String code;

}
