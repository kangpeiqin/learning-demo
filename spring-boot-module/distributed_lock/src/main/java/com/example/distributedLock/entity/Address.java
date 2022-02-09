package com.example.distributedLock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.example.distributedLock.entity.base.BaseEntity;
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
public class Address extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "address_id", type = IdType.AUTO)
    private Integer addressId;

    private String address;

    private String address2;

    private String district;

    private Integer cityId;

    private String postalCode;

    private String phone;

    private String location;


}
