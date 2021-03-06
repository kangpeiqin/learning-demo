package com.example.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mall.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统资源表 Mapper 接口
 * </p>
 *
 * @author kpq
 * @since 2022-03-13
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 获取所有资源及拥有这些资源的角色列表
     *
     * @return
     */
    List<Resource> getResourceWithRole();


    /**
     * 根据用户 id 获取资源菜单列表
     *
     * @param userId
     * @return
     */
    List<Resource> getResourceByUserId(@Param("userId") Integer userId);

}
