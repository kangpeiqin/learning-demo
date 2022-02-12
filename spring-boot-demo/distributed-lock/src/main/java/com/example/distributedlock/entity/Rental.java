package com.example.distributedlock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;

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
public class Rental extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rental_id", type = IdType.AUTO)
    private Integer rentalId;

    private LocalDateTime rentalDate;

    private Integer inventoryId;

    private Integer customerId;

    private LocalDateTime returnDate;

    private Integer staffId;


}
