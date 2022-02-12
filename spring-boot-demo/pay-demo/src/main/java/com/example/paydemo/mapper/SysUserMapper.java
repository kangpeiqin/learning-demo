package com.example.paydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.paydemo.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author KPQ
 * @date 2021/12/24
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
