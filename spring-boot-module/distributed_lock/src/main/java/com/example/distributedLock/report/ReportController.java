package com.example.distributedLock.report;

import com.example.distributedLock.util.ReportUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author KPQ
 * @date 2022/2/10
 */
@RestController
@Slf4j
public class ReportController {

    @Value("${report.location}")
    private String location;

    @GetMapping("/getPdf")
    public void getPdf(HttpServletResponse response) {
        final String srcName = "movie";
        ReportUtil.generatePdf(location, srcName, Maps.newHashMap(), response);
    }

}
