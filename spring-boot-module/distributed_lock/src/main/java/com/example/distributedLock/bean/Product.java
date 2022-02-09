package com.example.distributedLock.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author KPQ
 * @date 2022/1/24
 */
@Data
@TableName("product")
public class Product {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品库存
     */
    private Integer num;

    /**
     * 已售件数
     */
    private Integer sale;

    /**
     * 版本号
     */
    private Integer version;
}
