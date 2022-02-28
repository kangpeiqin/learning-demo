package com.example.distributedlock.search;

import com.example.distributedlock.search.domain.EsFilm;
import com.example.distributedlock.search.service.EsDataOpService;
import com.example.distributedlock.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author KPQ
 * @date 2022/2/28
 */
@RestController
@RequestMapping("/esFilm")
public class EsFilmController {

    @Autowired
    private EsDataOpService esDataOpService;

    @PostMapping("/importAll")
    public ResponseEntity importAllList() {
        int count = esDataOpService.importAll();
        return Result.success(count);
    }

    @GetMapping(value = "/search/simple")
    @ResponseBody
    public ResponseEntity<Page<EsFilm>> search(@RequestParam(required = false) String keyword,
                                                   @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsFilm> esProductPage = esDataOpService.search(keyword, pageNum, pageSize);
        return Result.success(esProductPage);
    }

}
