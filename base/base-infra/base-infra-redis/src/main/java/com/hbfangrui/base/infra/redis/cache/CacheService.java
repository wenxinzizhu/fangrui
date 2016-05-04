package com.hbfangrui.base.infra.redis.cache;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by tao.li on 2015/11/2.
 */
public interface CacheService<K,V> {
    void set(K key, V value);

    void set(K key, V value, long timeToLive, TimeUnit timeUnit);

    void del(K key);

    CompletableFuture<CacheItem<K,V>> get(K key);

    CompletableFuture<CacheItem<K,V>>
    get(K key, Function<K, V> loader);

    CompletableFuture<CacheItems<K,V>> get(Collection<K> keys);

    CompletableFuture<CacheItems<K,V>> get(Collection<K> keys, Function<K, V> loader);

}
