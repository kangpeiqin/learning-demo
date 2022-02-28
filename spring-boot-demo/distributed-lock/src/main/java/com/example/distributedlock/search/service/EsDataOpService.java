package com.example.distributedlock.search.service;

import com.example.distributedlock.search.domain.EsFilm;
import org.springframework.data.domain.Page;

/**
 * @author KPQ
 * @date 2022/2/28
 */
public interface EsDataOpService {

    /**
     * 从数据库同步数据到es钟
     */
    int importAll();

    /**
     * 根据id创建
     */
    EsFilm create(Long id);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsFilm> search(String keyword, Integer pageNum, Integer pageSize);

}
