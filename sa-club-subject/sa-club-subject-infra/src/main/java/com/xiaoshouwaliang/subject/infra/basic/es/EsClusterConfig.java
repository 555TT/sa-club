package com.xiaoshouwaliang.subject.infra.basic.es;

import lombok.Data;

/**
 * es集群类
 * @author 小手WA凉
 * @date 2024-05-10
 */
@Data
public class EsClusterConfig {
    /**
     * 集群名称
     */
    private String name;
    /**
     * 集群节点
     */
    private String nodes;
}
