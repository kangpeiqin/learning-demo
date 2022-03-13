package com.example.mall.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色资源表
 * </p>
 *
 * @author kpq
 * @since 2022-03-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="RoleResource对象", description="角色资源表")
public class RoleResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer resourceId;


}
