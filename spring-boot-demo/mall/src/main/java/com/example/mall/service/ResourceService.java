package com.example.mall.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mall.entity.Resource;
import com.example.mall.mapper.ResourceMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统资源表 服务实现类
 * </p>
 *
 * @author kpq
 * @since 2022-03-13
 */
@Service
@CacheConfig(cacheNames = "resource_cache")
public class ResourceService extends ServiceImpl<ResourceMapper, Resource> {

    @Cacheable
    public List<Resource> getAllMenusWithRole() {
        return getBaseMapper().getResourceWithRole();
    }

}
