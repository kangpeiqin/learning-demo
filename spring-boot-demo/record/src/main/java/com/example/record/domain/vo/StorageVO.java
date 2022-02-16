package com.example.record.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author kpq
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class StorageVO {

    private String relativePath;

    private String absolutePath;

}
