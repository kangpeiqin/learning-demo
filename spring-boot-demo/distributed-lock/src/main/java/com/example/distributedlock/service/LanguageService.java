package com.example.distributedlock.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.distributedlock.entity.Language;
import com.example.distributedlock.mapper.LanguageMapper;
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
public class LanguageService extends ServiceImpl<LanguageMapper, Language>  {

}
