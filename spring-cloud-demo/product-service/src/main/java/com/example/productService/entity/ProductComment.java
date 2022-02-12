package com.example.productService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_product_comment")
@ToString
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer","handler"})
public class ProductComment implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private Long authorId;
    private String content;
    private Date created;

}