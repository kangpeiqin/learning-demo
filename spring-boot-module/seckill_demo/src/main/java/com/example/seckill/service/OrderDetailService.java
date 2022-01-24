package com.example.seckill.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seckill.bean.OrderDetail;
import com.example.seckill.bean.Product;
import com.example.seckill.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author KPQ
 * @date 2022/1/24
 */
@Service
public class OrderDetailService extends ServiceImpl<OrderDetailMapper, OrderDetail> {

    public void create(Product product) {
        OrderDetail detail = new OrderDetail();
        detail.setName(product.getName()).setProdId(product.getId())
                .setCreateTime(Instant.now());
        this.save(detail);
    }

}
