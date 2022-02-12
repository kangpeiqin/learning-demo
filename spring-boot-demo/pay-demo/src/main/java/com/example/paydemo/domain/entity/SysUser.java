package com.example.paydemo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author KPQ
 * @date 2021/12/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_user")
public class SysUser extends BaseEntity {

    /**
     * 用户名
     */
    private String userName;


    /**
     * 密码
     */
    private String password;


    /**
     * 电话号码
     */
    private String mobile;

}
