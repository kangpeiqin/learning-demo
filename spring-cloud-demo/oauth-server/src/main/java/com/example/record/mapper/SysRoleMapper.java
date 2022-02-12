package com.example.record.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.record.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author KPQ
 * @date 2021/11/17
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id查找用户角色
     *
     * @param userId
     * @return
     */
    List<SysRole> findUserRoles(@Param("userId") Long userId);
}
