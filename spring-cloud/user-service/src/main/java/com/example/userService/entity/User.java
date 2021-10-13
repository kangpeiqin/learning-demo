package com.example.userService.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author KPQ
 * @date 2021/10/13
 */
@Entity
@Table(name = "tb_user")
@ToString
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer","handler"})
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String avatar;

}
