package com.example.mall.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author kpq
 * @since 2022-03-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="Role对象", description="系统角色表")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色 CODE")
    private String code;

    @ApiModelProperty(value = "角色名称")
    private String name;


}
