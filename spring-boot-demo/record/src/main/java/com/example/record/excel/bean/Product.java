package com.example.record.excel.bean;

import com.example.record.excel.poi.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.*;

/**
 * @author KPQ
 * @since 1.0
 */
@Data
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "product")
public class Product {

    @XmlTransient
    private Long id;

    @Excel(name = "产品名称", sort = 2)
    @XmlAttribute(name = "productName")
    private String productName;

    @Excel(name = "产品编号", sort = 1)
    private String productNum;

}
