package com.example.productService.repository;

import com.example.productService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author KPQ
 * @date 2021/10/13
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
