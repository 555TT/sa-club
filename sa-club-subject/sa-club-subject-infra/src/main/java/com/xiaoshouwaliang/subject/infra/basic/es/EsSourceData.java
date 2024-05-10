package com.xiaoshouwaliang.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 小手WA凉
 * @date 2024-05-10
 */
@Data
public class EsSourceData implements Serializable {
    private String docId;

    private Map<String, Object> data;
}
