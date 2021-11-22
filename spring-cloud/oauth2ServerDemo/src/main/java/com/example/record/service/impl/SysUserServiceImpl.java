package com.example.record.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.record.entity.SysRole;
import com.example.record.entity.SysUser;
import com.example.record.mapper.SysRoleMapper;
import com.example.record.mapper.SysUserMapper;
import com.example.record.service.SysUserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author KPQ
 * @date 2021/11/17
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
        List<SysRole> roleList = sysRoleMapper.findUserRoles(sysUser.getId());
        List<SimpleGrantedAuthority> roles = roleList.stream().map(e -> new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList());
        return new User(sysUser.getUsername(), sysUser.getPassword(), roles);
    }

}
