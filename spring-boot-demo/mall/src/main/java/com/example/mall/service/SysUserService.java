package com.example.mall.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mall.entity.Resource;
import com.example.mall.entity.Role;
import com.example.mall.entity.SysUser;
import com.example.mall.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kpq
 * @since 2022-03-13
 */
@Service
@RequiredArgsConstructor
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements UserDetailsService {

    private final RoleService roleService;

    private final ResourceService resourceService;

    private static final Integer PARENT_NODE = 0;

    /**
     * 根据用户名查找用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = this.getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUsername, username));
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        List<Role> roles = roleService.getBaseMapper().getUserRolesById(user.getId());
        user.setRoles(roles);
        List<Resource> resources = getResourceByUserId(user.getId());
        user.setResourceList(getResourceTree(resources, PARENT_NODE));
        return user;
    }

    private List<Resource> getResourceByUserId(Integer userId) {
        List<Resource> resourceList = resourceService.getBaseMapper()
                .getResourceByUserId(userId);
        return resourceList;
    }


    private List<Resource> getResourceTree(List<Resource> resources, Integer parentId) {
        List<Resource> resourceList = new ArrayList<>();
        for (Resource resource : resources) {
            if (resource.getParentId().equals(parentId)) {
                resource.setChildren(getResourceTree(resources, resource.getId()));
                resourceList.add(resource);
            }
        }
        return resourceList;
    }

}
