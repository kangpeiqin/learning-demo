package com.example.distributedLock.service;

import com.example.distributedLock.entity.Store;
import com.example.distributedLock.mapper.StoreMapper;
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
public class StoreService extends ServiceImpl<StoreMapper, Store>  {

}
