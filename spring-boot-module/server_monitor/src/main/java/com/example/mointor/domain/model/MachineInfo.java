package com.example.mointor.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KPQ
 */
@Data
@Accessors(chain = true)
public class MachineInfo {

    /**
     * 操作系统类型
     */
    private String operatingSystem;

    /**
     * 内存大小
     */
    private String totalMemory;

    /**
     * 内存类型
     */
    private String ramType;

    /**
     * 线程数
     */
    private int processCnt;

}
