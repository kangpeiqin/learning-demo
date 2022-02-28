package com.example.distributedlock.search.service;

import com.example.distributedlock.entity.Film;
import com.example.distributedlock.search.domain.EsFilm;
import com.example.distributedlock.search.repository.EsFilmRepository;
import com.example.distributedlock.service.FilmService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author KPQ
 * @date 2022/2/28
 */
@Service
public class EsDataOpServiceImpl implements EsDataOpService {

    @Autowired
    private FilmService filmService;

    @Autowired
    private EsFilmRepository filmRepository;

    @Override
    public int importAll() {
        List<Film> filmList = filmService.list();
        List<EsFilm> list = filmList.stream().map(e -> {
            EsFilm esFilm = new EsFilm();
            BeanUtils.copyProperties(e, esFilm);
            return esFilm;
        }).collect(Collectors.toList());
        Iterable<EsFilm> esFilmIterable = filmRepository.saveAll(list);
        Iterator iterator = esFilmIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public EsFilm create(Long id) {
        return null;
    }

    @Override
    public Page<EsFilm> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return filmRepository.findEsFilmByTitleOrDescription(keyword, keyword, keyword, pageable);
    }

}
