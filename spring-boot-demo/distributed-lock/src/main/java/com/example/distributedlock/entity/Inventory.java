package com.example.distributedlock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.example.distributedlock.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author kpq
 * @since 2022-02-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Inventory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "inventory_id", type = IdType.AUTO)
    private Integer inventoryId;

    private Integer filmId;

    private Integer storeId;


}
