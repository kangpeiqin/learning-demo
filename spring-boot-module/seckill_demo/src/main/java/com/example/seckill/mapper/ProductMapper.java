package com.example.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seckill.bean.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author KPQ
 * @date 2022/1/24
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 使用表锁，减库存
     *
     * @return
     */
    int decreaseStock();
}
