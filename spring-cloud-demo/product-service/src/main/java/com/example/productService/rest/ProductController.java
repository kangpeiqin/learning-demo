package com.example.productService.rest;

import com.example.productService.entity.Product;
import com.example.productService.entity.ProductComment;
import com.example.productService.entity.User;
import com.example.productService.repository.ProductCommentRepository;
import com.example.productService.repository.ProductRepository;
import com.example.productService.service.UserService;
import com.example.productService.vo.ProductCommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author KPQ
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCommentRepository productCommentRepository;

    @Resource
    private UserService userService;

    @GetMapping("all")
    public List<Product> list() {
        return this.productRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product detail(@PathVariable("id") Long id) {
        Product product = this.productRepository.getOne(id);
        return product;
    }

    @GetMapping(value = "/{id}/comments")
    public List<ProductCommentVO> comments(@PathVariable Long id) {
        List<ProductComment> commentList = this.productCommentRepository.findByProductIdOrderByCreated(id);
        if (null == commentList || commentList.isEmpty()) {
            return Collections.emptyList();
        }
        return commentList.stream().map((comment) -> {
            User user = this.userService.load(comment.getAuthorId());
            ProductCommentVO vo = new ProductCommentVO().setUser(user);
            BeanUtils.copyProperties(comment,vo);
            return vo;
        }).collect(Collectors.toList());
    }


}
