package com.example.payDemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.payDemo.domain.entity.TradeOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author KPQ
 * @date 2021/12/24
 */
@Mapper
public interface TradeOrderMapper extends BaseMapper<TradeOrder> {
}
