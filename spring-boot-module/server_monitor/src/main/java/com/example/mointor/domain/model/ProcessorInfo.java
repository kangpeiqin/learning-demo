package com.example.mointor.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KPQ
 * @date 2021/12/7
 */
@Data
@Accessors(chain = true)
public class ProcessorInfo {

    private String name;

    private String coreNum;

    private String clockSpeed;

    private String bitDepth;

}
