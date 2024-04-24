package com.xiaoshouwaliang.oss.entity;

import lombok.Data;

/**
 * 文件类
 *
 */
@Data
public class FileInfo {

    private String fileName;

    private Boolean directoryFlag;

    private String etag;

}
