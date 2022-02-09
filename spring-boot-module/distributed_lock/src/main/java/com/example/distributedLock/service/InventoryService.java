package com.example.distributedLock.service;

import com.example.distributedLock.entity.Inventory;
import com.example.distributedLock.mapper.InventoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kpq
 * @since 2022-02-09
 */
@Service
public class InventoryService extends ServiceImpl<InventoryMapper, Inventory>  {

}
