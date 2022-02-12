package com.example.distributedlock.entity;

import java.math.BigDecimal;
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
public class Payment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "payment_id", type = IdType.AUTO)
    private Integer paymentId;

    private Integer customerId;

    private Integer staffId;

    private Integer rentalId;

    private BigDecimal amount;

    private LocalDateTime paymentDate;


}
