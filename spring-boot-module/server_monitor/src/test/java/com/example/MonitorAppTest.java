package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

/**
 * @author KPQ
 * @date 2021/10/12
 */
@Slf4j
public class MonitorAppTest {

    private SystemInfo systemInfo;

    @BeforeEach
    public void getSystemInfo() {
        systemInfo = new SystemInfo();
    }

    @Test
    void processorInfo() {
        CentralProcessor centralProcessor = systemInfo.getHardware().getProcessor();
        System.out.println(centralProcessor.getLogicalProcessorCount());
    }

}
