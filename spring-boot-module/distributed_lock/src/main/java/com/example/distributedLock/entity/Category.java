package com.example.distributedLock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.distributedLock.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author kpq
 * @since 2022-02-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    private String name;


}
