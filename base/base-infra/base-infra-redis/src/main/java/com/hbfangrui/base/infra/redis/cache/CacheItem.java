package com.hbfangrui.base.infra.redis.cache;

import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Created by tao.li on 2015/11/2.
 */
@Value
@EqualsAndHashCode(exclude = "value")
public class CacheItem<K,V> {
    private final K key;
    private final V value;

    private CacheItem(K key, V value) {
        Preconditions.checkArgument(key != null, "key can not be null");
        this.key = key;
        this.value = value;
    }

    public static <K,V>CacheItem<K,V> apply(K key, V value){
        return new CacheItem<>(key, value);
    }

    public static <K,V> CacheItem<K,V> apply(K key){
        return apply(key, null);
    }

    public boolean isCached() {
        return this.value != null;
    }
}
