package com.xiaoshouwaliang.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 小手WA凉
 * @date 2024-05-10
 */
@Data
public class EsIndexInfo implements Serializable {
    /**
     * 集群名称
     */
    private String clusterName;
    /**
     * 索引名称
     */
    private String indexName;
}
