package com.example.mointor.service;

import com.example.mointor.domain.dto.UsageDTO;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.util.Util;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author KPQ
 * @date 2021/12/7
 */
@Service
public class UsageService {

    @Resource
    private SystemInfo systemInfo;

    public UsageDTO getUsage() {
        UsageDTO usageDTO = new UsageDTO()
                .setProcessor(getProcessorUsage())
                .setRam(getMemoryUsage())
                .setStorage(getStorageUsage());
        return usageDTO;
    }

    private int getMemoryUsage() {
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        long total = globalMemory.getTotal();
        long available = globalMemory.getAvailable();
        return (int) Math.round(100 - (((double) available / total) * 100));
    }

    private int getStorageUsage() {
        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();
        long totalStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getTotalSpace).sum();
        long freeStorage = fileSystem.getFileStores().stream().mapToLong(OSFileStore::getFreeSpace).sum();
        return (int) Math.round(((double) (totalStorage - freeStorage) / totalStorage) * 100);
    }

    private int getProcessorUsage() {
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();
        long[] prevTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long prevTotalTicks = Arrays.stream(prevTicksArray).sum();
        long prevIdleTicks = prevTicksArray[CentralProcessor.TickType.IDLE.getIndex()];
        Util.sleep(1000);
        long[] currTicksArray = centralProcessor.getSystemCpuLoadTicks();
        long currTotalTicks = Arrays.stream(currTicksArray).sum();
        long currIdleTicks = currTicksArray[CentralProcessor.TickType.IDLE.getIndex()];
        return (int) Math.round((1 - ((double) (currIdleTicks - prevIdleTicks)) / ((double) (currTotalTicks - prevTotalTicks))) * 100);
    }
}
