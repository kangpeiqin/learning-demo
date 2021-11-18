package com.example.demo.repository;

import com.example.demo.domain.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author KPQ
 * @date 2021/11/18
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    /**
     * 通过用户名查找用户.
     *
     * @param username 用户名
     * @return 结果
     */
    Optional<SysUser> findFirstByUsername(String username);

}
