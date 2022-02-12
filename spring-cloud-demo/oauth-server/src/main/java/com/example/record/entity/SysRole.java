package com.example.record.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author KPQ
 * @date 2021/11/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends BaseEntity {


    /**
     * 角色名称，按照 spring security 规范，需要以 ROLE_ 开头.
     */
    private String name;

    /**
     * 角色描述.
     */
    private String description;


}
