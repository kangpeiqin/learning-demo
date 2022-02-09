package com.example.distributedLock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.distributedLock.entity.base.BaseEntity;
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
public class Actor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "actor_id", type = IdType.AUTO)
    private Integer actorId;

    private String firstName;

    private String lastName;


}
