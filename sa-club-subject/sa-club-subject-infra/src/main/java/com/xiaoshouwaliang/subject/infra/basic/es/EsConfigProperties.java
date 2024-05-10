package com.xiaoshouwaliang.subject.infra.basic.es;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-05-10
 */
@Component
@ConfigurationProperties(prefix = "es.cluster")
public class EsConfigProperties {
    List<EsClusterConfig> esConfigs =new ArrayList<>();

    public List<EsClusterConfig> getEsConfigs() {
        return esConfigs;
    }

    public void setEsConfigs(List<EsClusterConfig> esConfigs) {
        this.esConfigs = esConfigs;
    }
}
