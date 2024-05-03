package com.xiaoshouwaliang.subject.domain.util;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**本地缓存工具类
 * @author 小手WA凉
 * @date 2024-05-03
 */
@Component
public class CacheUtil<T,V>{
    private Cache<String,String> localCache= CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();

    public List<V> getResult(String cacheKey, Class<V> clazz
                            , Function<String,List<V>> function){
        List<V> resultList;
        String value = localCache.getIfPresent(cacheKey);
        if(StringUtils.isNotBlank(value)){
            resultList= JSON.parseArray(value,clazz);
        }else {
            resultList= function.apply(cacheKey);
            if (!CollectionUtils.isEmpty(resultList)) {
                localCache.put(cacheKey, JSON.toJSONString(resultList));
            }
        }
        return resultList;
    }
}
