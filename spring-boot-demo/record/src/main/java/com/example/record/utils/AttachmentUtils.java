package com.example.record.utils;

import com.example.record.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author kpq
 * @since 1.0.0
 */
@Slf4j
public class AttachmentUtils {

    private AttachmentUtils() {
    }

    /**
     * 附件上传
     *
     * @param file   源文件
     * @param path   上传路径
     * @param rename 是否重命名源文件
     * @return 上传路径
     */
    public static String upload(@NonNull MultipartFile file, String path, boolean rename) {
        Path uploadPath = Paths.get(path);
        try {
            //文件存放路径不存在，则创建路径
            Files.createDirectories(uploadPath);
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = rename ? UUID.randomUUID().toString().replaceAll("-", "")
                    + '.' + extension : file.getOriginalFilename();
            String fullPath = path + fileName;
            Path filePath = Paths.get(fullPath);
            //非重命名上传时，如果重名，则覆盖原来的文件
            if (!rename) {
                Files.deleteIfExists(filePath);
            }
            Files.createFile(filePath);
            file.transferTo(filePath);
            return fullPath;
        } catch (Exception e) {
            log.error("upload file error:{}", e.getMessage());
            throw new BusinessException("upload file error:{} ", e.getMessage());
        }
    }

    public void remove(@NonNull String path) {
        Path filePath = Paths.get(path);
        try {
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new BusinessException("error remove file:{}", e.getMessage());
        }
    }

}
