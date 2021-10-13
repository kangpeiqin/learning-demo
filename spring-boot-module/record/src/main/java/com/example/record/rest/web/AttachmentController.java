package com.example.record.rest.web;

import cn.hutool.core.io.FileUtil;
import com.example.record.service.AttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author kpq
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/attachment")
@Slf4j
@Api(tags = "文件管理")
public class AttachmentController {

    @Resource
    private AttachmentService attachmentService;

    @PostMapping("upload")
    @ApiOperation("文件上传")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(attachmentService.upload(file, true));
    }

    @GetMapping("/fileDownload")
    @ApiOperation("文件下载")
    public ResponseEntity fileDownload(@RequestParam("path") @NonNull String path, String fileName, HttpServletResponse response) throws IOException {
        try (OutputStream out = response.getOutputStream(); BufferedInputStream bis = FileUtil.getInputStream(path)) {
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.setHeader("content-filedownloadname", URLEncoder.encode(fileName, "utf-8"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            byte[] buff = new byte[1024];
            int read = bis.read(buff);
            while (read != -1) {
                out.write(buff, 0, buff.length);
                out.flush();
                read = bis.read(buff);
            }
        } catch (Exception e) {
            log.error("下载文件异常：{}", e.getMessage());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
