package com.example.demo.security.service;

import com.example.demo.domain.entity.SysUser;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.SysUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author KPQ
 * @date 2021/11/18
 */
@Service
@RequiredArgsConstructor
public class SysUserService implements UserDetailsService {

    private final SysUserRepository sysUserRepository;

    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepository.findFirstByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        List<SimpleGrantedAuthority> roles = sysUser.getRoles().stream().map(sysRole -> new SimpleGrantedAuthority(sysRole.getName())).collect(Collectors.toList());
        return new User(sysUser.getUsername(), sysUser.getPassword(), roles);
    }

    public void save(SysUser user) {
        Optional<SysUser> sysUser = sysUserRepository.findFirstByUsername(user.getUsername());
        if (sysUser.isPresent()) {
            throw new BusinessException("用户名已存在");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        sysUserRepository.save(user);
    }

}
