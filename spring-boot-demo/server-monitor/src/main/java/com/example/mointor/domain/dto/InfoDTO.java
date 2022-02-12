package com.example.mointor.domain.dto;

import com.example.mointor.domain.model.MachineInfo;
import com.example.mointor.domain.model.ProcessorInfo;
import com.example.mointor.domain.model.StorageInfo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author KPQ
 * @date 2021/12/7
 */
@Data
@Accessors(chain = true)
public class InfoDTO {

    private ProcessorInfo processorInfo;

    private MachineInfo machineInfo;

    private StorageInfo storageInfo;

}
