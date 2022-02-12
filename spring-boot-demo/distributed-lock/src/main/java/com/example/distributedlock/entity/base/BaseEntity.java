package com.example.distributedlock.entity.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;

/**
 * @author KPQ
 * @date 2022/2/9
 */
@Data
public class BaseEntity {

    /**
     * 最后更新时间
     */
    @TableField(value = "last_update")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant lastUpdate;

}
