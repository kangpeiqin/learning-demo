package com.example.seckill.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seckill.bean.Product;
import com.example.seckill.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author KPQ
 * @date 2022/1/24
 */
@Service
@RequiredArgsConstructor
public class ProductService extends ServiceImpl<ProductMapper, Product> {

    private final OrderDetailService orderDetailService;

    /**
     * 会出现超卖现象
     */
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(int prodId) {
        //库存检查
        Product prod = this.getById(prodId);
        Assert.isTrue(prod.getNum() > 0, "库存不足");
        //减库存，更新库存，非原子性，会出现问题
        prod.setNum(prod.getNum() - 1);
        prod.setSale(prod.getSale() + 1);
        this.updateById(prod);
        //订单详情表创建
        orderDetailService.create(prod);
    }
}
