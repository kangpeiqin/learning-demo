package com.example.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mall.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author kpq
 * @since 2022-03-13
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id，获取用户角色
     * @param userId
     * @return
     */
    List<Role> getUserRolesById(@Param("userId") Integer userId);
}
