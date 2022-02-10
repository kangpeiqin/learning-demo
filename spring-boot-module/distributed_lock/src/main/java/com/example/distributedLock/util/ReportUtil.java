package com.example.distributedLock.util;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperRunManager;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.sql.Connection;
import java.util.Map;

/**
 * 报表生成工具类
 *
 * @author KPQ
 * @date 2022/2/10
 */
@Slf4j
public class ReportUtil {


    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();


    private ReportUtil() {
    }

    private static DataSource dataSource = SpringUtil.getBean(DataSource.class);

    private static final String DEST_SUFFIX = ".jasper";

    private static final String SOURCE_SUFFIX = ".jrxml";

    public static void generatePdf(String location, String srcName, Map<String, Object> parameters, HttpServletResponse response) {
        InputStream inputStream = compile(location, srcName);
        try (ServletOutputStream outputStream = response.getOutputStream(); Connection connection = dataSource.getConnection()) {
            JasperRunManager.runReportToPdfStream(inputStream, outputStream, parameters, connection);
            outputStream.flush();
            response.setHeader("Content-Disposition", "inline;filename=" + srcName + ".pdf");
            response.addHeader("charset", "UTF-8");
            response.setContentType("application/pdf");
        } catch (Exception e) {
            log.error("导出pdf文件:{}出错：{}", srcName, e.getMessage(), e);
            throw new RuntimeException("导出pdf文件出错", e);
        }
    }

    /**
     * 由 .jrxml 文件编译生成 .jasper 文件流
     *
     * @param location 源文件存储目录
     * @param fileName 源文件名
     * @return
     */
    private static InputStream compile(String location, String fileName) {
        final String srcName = location + fileName + SOURCE_SUFFIX;
        final String destName = location + fileName + DEST_SUFFIX;
        try {
            URI destUri = resourceResolver.getResource(destName).getURI();
            //编译文件,生成 .jasper 文件
            JasperCompileManager.compileReportToStream(resourceResolver.getResource(srcName).getInputStream(),
                    new FileOutputStream(destUri.getPath()));
            return new FileInputStream(new File(destUri));
        } catch (Exception e) {
            log.error("编译报表文件{}出错：{}", srcName, e.getMessage(), e);
            throw new RuntimeException("编译报表文件出错", e);
        }
    }

}
