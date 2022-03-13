package com.example.mall.entity;

import com.example.mall.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author kpq
 * @since 2022-03-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UserRole对象", description="用户角色表")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "系统用户id")
    private Integer userId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;


}
