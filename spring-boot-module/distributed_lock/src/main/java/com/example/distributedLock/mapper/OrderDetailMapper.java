package com.example.distributedLock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.distributedLock.bean.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kangpeiqin
 * @date 2022/1/24
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
