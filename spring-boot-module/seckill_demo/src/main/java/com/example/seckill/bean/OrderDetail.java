package com.example.seckill.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * 商品订单详情表
 *
 * @author KPQ
 * @date 2022/1/24
 */
@Data
@TableName("order_detail")
@Accessors(chain = true)
public class OrderDetail {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer prodId;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant createTime;

}
