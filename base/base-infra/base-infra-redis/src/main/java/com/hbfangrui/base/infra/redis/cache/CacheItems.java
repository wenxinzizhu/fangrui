package com.hbfangrui.base.infra.redis.cache;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tao.li on 2015/11/2.
 */
public class CacheItems<K, V> implements Iterable<CacheItem<K, V>> {
    private final Map<K, CacheItem<K, V>> cacheData = Maps.newHashMap();

    public CacheItems() {
    }
    public CacheItems(CacheItem<K, V> cacheItem) {
        addCacheItem(cacheItem);
    }

    private void addCacheItem(CacheItem<K, V> cacheItem) {
        if (cacheItem != null) {
            this.cacheData.put(cacheItem.getKey(), cacheItem);
        }
    }

    public CacheItems(Collection<CacheItem<K, V>> cacheItems) {
        cacheItems.forEach(this::addCacheItem);
    }

    @Override
    public Iterator<CacheItem<K, V>> iterator() {
        return this.cacheData.values().iterator();
    }

    public CacheItem<K, V> getCacheItem(K key) {
        return this.cacheData.getOrDefault(key, CacheItem.apply(key));
    }

    public static <K, V> CacheItems<K, V> apply() {
        return new CacheItems<>();
    }

    public static <K, V> CacheItems<K, V> apply(CacheItem<K, V> cacheItem) {
        return new CacheItems<>(cacheItem);
    }

    public static <K, V> CacheItems<K, V> apply(List<CacheItem<K, V>> items) {
        return new CacheItems<>(items);
    }
}
