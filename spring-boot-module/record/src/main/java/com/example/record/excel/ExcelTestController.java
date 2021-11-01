package com.example.record.excel;

import com.example.record.excel.bean.Product;
import com.example.record.excel.poi.ExcelHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author KPQ
 * @date 2021/10/31
 */
@RestController
@Slf4j
public class ExcelTestController {

    @GetMapping("export")
    @ApiOperation("excel导出")
    public ResponseEntity export(HttpServletResponse response) {
        List<Product> productList = Lists.newArrayList();
        Product product = new Product()
                .setProductName("产品").setProductNum("12345678");
        productList.add(product);
        return new ExcelHelper<>(Product.class).export("产品表", productList, response);
    }

    @GetMapping("exportMulti")
    @ApiOperation("多sheet页excel导出")
    public ResponseEntity exportMultiSheet(HttpServletResponse response) {
        List<Product> productList = Lists.newArrayList();
        Product product = new Product().setProductName("产品").setProductNum("12345678");
        productList.add(product);
        Map<String, List<Product>> map = Maps.newHashMap();
        map.put("sheet1", productList);
        map.put("sheet2", productList);
        return new ExcelHelper<>(Product.class).exportMultiSheetExcel("产品表", map, response);
    }

    @PostMapping("import")
    @ApiOperation("excel导入")
    public ResponseEntity importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        List<Product> productList = new ExcelHelper<>(Product.class).importExcel(file);
        log.info("导入Excel数据：{}", productList);
        return ResponseEntity.ok("");
    }
}
