package com.example.distributedlock.search.repository;

import com.example.distributedlock.search.domain.EsFilm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author KPQ
 * @date 2022/2/28
 */
public interface EsFilmRepository extends ElasticsearchRepository<EsFilm, Integer> {

    /**
     * 根据关键字查找
     *
     * @param title
     * @param description
     * @param keywords
     * @param page
     * @return
     */
    Page<EsFilm> findEsFilmByTitleOrDescription(String title, String description, String keywords, Pageable page);

}
