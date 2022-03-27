/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springsecurity.cache;

import com.grapefruit.localcache.cache.LocalCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 本地缓存
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-07-17 11:21 上午
 */
@Component
public class GrapeCache {

    @Autowired
    private LocalCache localCache;

    public <T> T getObj(String rowKey,String columnKey,Class<T> t) {
        return t.cast(localCache.getObjCache().get(rowKey, columnKey));
    }
}
