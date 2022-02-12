package com.example.record.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 系统用户：保存当前系统用户的信息
 *
 * @author KPQ
 * @date 2021/11/17
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@Accessors(chain = true)
@Data
public class SysUser extends BaseEntity {

    /**
     * 用户名.
     */
    private String username;

    /**
     * 密码.
     */
    private String password;


    @TableField(exist = false)
    private List<SysRole> roleList;

}
