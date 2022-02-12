package com.example.mointor.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KPQ
 * @date 2021/12/7
 */
@Data
@Accessors(chain = true)
public class UsageDTO {

    /**
     * 使用的cpu核数
     */
    private Integer processor;

    /**
     * 使用的内存
     */
    private Integer ram;

    /**
     * 使用的硬盘
     */
    private Integer storage;

}
