package com.example.record.rest.web;

import com.example.record.excel.bean.Product;
import com.example.record.utils.XmlUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * xml 文件解析
 *
 * @author KPQ
 * @date 2021/11/10
 */
@RestController
public class XMLParserController {

    @PostMapping("xml-parser")
    @ApiOperation("文件上传")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) throws IOException {
        String xml = IOUtils.toString(file.getInputStream(), StandardCharsets.UTF_8);
        Product product = XmlUtil.toObject(xml, Product.class);
        return ResponseEntity.ok(product);
    }

}
