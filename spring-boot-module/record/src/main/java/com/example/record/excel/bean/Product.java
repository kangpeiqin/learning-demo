package com.example.record.excel.bean;

import com.example.record.excel.poi.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KPQ
 * @since 1.0
 */
@Data
@Accessors(chain = true)
public class Product {

    private Long id;

    @Excel(name = "产品名称", sort = 2)
    private String productName;

    @Excel(name = "产品编号", sort = 1)
    private String productNum;

}
