package com.example.distributedlock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;

import com.example.distributedlock.entity.base.BaseEntity;
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
public class Staff extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "staff_id", type = IdType.AUTO)
    private Integer staffId;

    private String firstName;

    private String lastName;

    private Integer addressId;

    private Blob picture;

    private String email;

    private Integer storeId;

    private Boolean active;

    private String username;

    private String password;


}
