package com.example.seckill.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seckill.bean.Product;
import com.example.seckill.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author KPQ
 * @date 2022/1/24
 * 并发事务问题(在加锁的情况下，加事务注解会引发的一些问题)，MySQL 默认的事务隔离级别：REPEATABLE-READ：可重复读，同时创建多个事务的情况
 * 使用 JMeter 进行测试
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService extends ServiceImpl<ProductMapper, Product> {

    private final OrderDetailService orderDetailService;

    /**
     * 使用锁(事务注解：并发事务问题)
     * 方法加锁，效率低，线程执行串行化
     */
    public synchronized void useLock(int prodId) {
        createOrder(prodId);
    }


    /**
     * 普通情况下：高并发下，这样减库存会引发一致性问题
     */
    //    @Transactional(rollbackFor = Exception.class)
    public void createOrder(int prodId) {
        // =======================情况1：==================================
        //库存检查，多个线程获取到的库存数据可能是一样的，这样就会导致后面减库存操作问题(减库存，更新库存，非原子性，会引发一致性问题)
        Product prod = checkStock(prodId);
        prod.setNum(prod.getNum() - 1);
        prod.setSale(prod.getSale() + 1);
        // =======================情况1==================================
        this.updateById(prod);
        //订单详情创建
        orderDetailService.create(prod);
    }

    /**
     * 库存检查
     *
     * @param prodId
     * @return
     */
    private Product checkStock(int prodId) {
        Product prod = this.getById(prodId);
        log.info("=============库存：{}========", prod.getNum());
        Assert.isTrue(prod.getNum() > 0, "库存不足");
        return prod;
    }

    /**
     * 使用悲观锁，数据库行锁
     */
    public void useDataBaseLock(int prodId) {
        //数据库行锁，库存检查,应该要确保原子性
        this.getBaseMapper().decreaseStock(prodId);
        Product prod = checkStock(prodId);
        orderDetailService.create(prod);
    }
}
