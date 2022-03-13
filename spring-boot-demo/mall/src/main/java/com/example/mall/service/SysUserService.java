package com.example.mall.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mall.entity.Role;
import com.example.mall.entity.SysUser;
import com.example.mall.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kpq
 * @since 2022-03-13
 */
@Service
@RequiredArgsConstructor
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements UserDetailsService {

    private final RoleService roleService;

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
        return user;
    }
}
