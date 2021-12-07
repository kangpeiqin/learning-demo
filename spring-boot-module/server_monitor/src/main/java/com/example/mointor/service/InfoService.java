package com.example.mointor.service;

import com.example.mointor.domain.dto.InfoDTO;
import com.example.mointor.domain.model.MachineInfo;
import com.example.mointor.domain.model.ProcessorInfo;
import com.example.mointor.domain.model.StorageInfo;
import com.example.mointor.util.ConverterUtil;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.PhysicalMemory;
import oshi.software.os.OperatingSystem;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author KPQ
 * @date 2021/12/7
 */
@Service
public class InfoService {

    @Resource
    private SystemInfo systemInfo;

    public InfoDTO getInfo() {
        InfoDTO infoDTO = new InfoDTO().setMachineInfo(getMachineInfo())
                .setProcessorInfo(getProcessor()).setStorageInfo(getStorage());
        return infoDTO;
    }

    /**
     * 获取 CPU 信息
     */
    private ProcessorInfo getProcessor() {
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();
        ProcessorInfo processorInfo = new ProcessorInfo()
                .setName(centralProcessor.getProcessorIdentifier().getName())
                .setCoreNum(centralProcessor.getLogicalProcessorCount() + "核")
                .setClockSpeed(ConverterUtil.getFrequency(centralProcessor.getCurrentFreq()))
                .setBitDepth((centralProcessor.getProcessorIdentifier().isCpu64bit() ? "64" : "32") + "位");
        return processorInfo;
    }

    /**
     * 获取操作系统、内存等信息
     */
    private MachineInfo getMachineInfo() {
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        OperatingSystem.OSVersionInfo versionInfo = operatingSystem.getVersionInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        MachineInfo machineInfo = new MachineInfo()
                .setOperatingSystem(operatingSystem.getFamily() + " " + versionInfo.getVersion())
                .setTotalMemory(ConverterUtil.getConvertedCapacity(globalMemory.getTotal()))
                .setProcessCnt(operatingSystem.getProcessCount());
        Optional<PhysicalMemory> physicalMemory = globalMemory.getPhysicalMemory().stream().findFirst();
        physicalMemory.ifPresent(memory -> machineInfo.setRamType(memory.getMemoryType()));
        return machineInfo;
    }

    /**
     * 获取硬盘信息
     */
    private StorageInfo getStorage() {
        List<HWDiskStore> hwDiskStores = systemInfo.getHardware().getDiskStores();
        long total = hwDiskStores.stream().mapToLong(HWDiskStore::getSize).sum();
        StorageInfo storageInfo = new StorageInfo();
        storageInfo.setDiskCount(hwDiskStores.size());
        storageInfo.setTotal(ConverterUtil.getConvertedCapacity(total));
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        storageInfo.setSwapAmount(ConverterUtil.getConvertedCapacity(globalMemory.getVirtualMemory().getSwapTotal()));
        return storageInfo;
    }


}
