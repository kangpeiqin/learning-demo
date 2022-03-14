package com.example.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 系统资源表
 * </p>
 *
 * @author kpq
 * @since 2022-03-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Resource对象", description = "系统资源表")
public class Resource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "后端链接")
    private String url;

    @ApiModelProperty(value = "前端路径")
    private String path;

    private String component;

    private String name;

    @ApiModelProperty(value = "资源图标")
    private String icon;

    @ApiModelProperty(value = "父级id，0 表示顶级")
    private Integer parentId;

    @ApiModelProperty(value = "账户是否可用")
    private Boolean enabled;

    private List<Resource> children;

    @JsonIgnore
    private List<Role> roles;

}
