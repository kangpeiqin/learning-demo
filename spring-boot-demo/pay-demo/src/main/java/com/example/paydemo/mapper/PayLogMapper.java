package com.example.paydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.paydemo.domain.entity.PayLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kangpeiqin
 * @date 2021/12/24
 */
@Mapper
public interface PayLogMapper extends BaseMapper<PayLog> {
}
