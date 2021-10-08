package com.example.record.service;

import com.example.record.config.AttachmentProperties;
import com.example.record.utils.AttachmentUtils;
import com.example.record.vo.StorageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author kpq
 * @since 1.0.0
 */
@Service
@Slf4j
public class AttachmentService {

    @Autowired
    private AttachmentProperties properties;

    public StorageVO upload(@NonNull MultipartFile file, boolean rename) {
        validate(file);
        String dir = properties.getLocation() + datePath();
        String relativePath = AttachmentUtils.upload(file, dir, rename);
        String absolutePath = properties.getDomain() + relativePath;
        StorageVO storage = new StorageVO().setRelativePath(relativePath)
                .setAbsolutePath(absolutePath);
        return storage;
    }

    private void validate(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        List<String> formatList = properties.getAllowFormat();
        int maxSize = properties.getMaxSize() * 1024 * 1024;
        Assert.isTrue(formatList.contains(extension), "请上传" + formatList.toString() + "格式");
        Assert.isTrue(file.getSize() <= maxSize, "上传文件大小不能超过" + properties.getMaxSize() + "M");
    }

    private static String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd") + "/";
    }

}
