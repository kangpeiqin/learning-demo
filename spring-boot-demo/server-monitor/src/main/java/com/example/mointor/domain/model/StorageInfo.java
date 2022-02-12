package com.example.mointor.domain.model;

import lombok.Data;

/**
 * 硬盘信息
 *
 * @author KPQ
 * @date 2021/12/7
 */
@Data
public class StorageInfo {

    /**
     * 硬盘总容量
     */
    private String total;

    /**
     * 硬盘数目
     */
    private int diskCount;


    /**
     * 虚拟内存交换
     */
    private String swapAmount;

}
