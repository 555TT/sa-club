package com.xiaoshouwaliang.subject.common.entity;

import java.io.Serializable;

/**
 * @author 小手WA凉
 * @date 2024-04-21
 */
public class PageInfo implements Serializable {
    private Integer pageNo = 1;

    private Integer pageSize = 20;

    public Integer getPageNo() {
        if (pageNo == null || pageNo < 1) {
            return 1;
        }
        return pageNo;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1 || pageSize > Integer.MAX_VALUE) {
            return 20;
        }
        return pageSize;
    }
}
