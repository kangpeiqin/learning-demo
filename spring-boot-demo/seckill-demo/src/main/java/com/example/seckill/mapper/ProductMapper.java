package com.example.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seckill.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author KPQ
 * @date 2022/1/24
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 使用表锁，减库存
     *
     * @param id
     * @return
     */
    int decreaseStock(@Param("id") Integer id);


    /**
     * 使用乐观锁进行更新
     *
     * @param id
     * @param version
     * @return
     */
    int updateByOptimistic(@Param("id") Integer id, @Param("version") Integer version);

}
