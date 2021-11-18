package com.example.demo.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * @author KPQ
 * @date 2021/11/18
 */
@Data
@Table
@Entity
@EqualsAndHashCode(exclude = {"users"})
@ToString(exclude = "users")
public class SysRole {

    /**
     * 主键.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称，按照 spring security 规范
     * 需要以 ROLE_ 开头.
     */
    private String name;

    /**
     * 角色描述.
     */
    private String description;

    /**
     * 当前角色所有用户.
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<SysUser> users;
}
