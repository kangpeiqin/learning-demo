package com.example.productService.vo;

import com.example.productService.entity.ProductComment;
import com.example.productService.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author KPQ
 * @date 2021/10/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ProductCommentVO extends ProductComment {

    private User user;

}
