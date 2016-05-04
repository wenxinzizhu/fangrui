package com.hbfangrui.base.infra.redis.cache;

import org.apache.commons.collections.CollectionUtils;
import org.redisson.RedissonClient;
import org.redisson.core.RBucket;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Created by tao.li on 2015/11/2.
 */
public class RedissonBasedCacheService<K, V> implements CacheService<K, V> {
    private final RedissonClient client;
    private final ExecutorService loaderExecutorService;
    private final Function<K, String> k2str;

    public RedissonBasedCacheService(RedissonClient client, ExecutorService loaderExecutorService){
        this(client, loaderExecutorService, key -> key.toString());
    }

    public RedissonBasedCacheService(RedissonClient client,
                                     ExecutorService loaderExecutorService, Function<K, String> k2str) {
        this.client = client;
        this.loaderExecutorService = loaderExecutorService;
        this.k2str = k2str;
    }
    private String toStr(K key){
        return this.k2str.apply(key);
    }

    @Override
    public void set(K key, V value) {
        this.client.getBucket(toStr(key)).set(value);
    }

    @Override
    public void set(K key, V value, long timeToLive, TimeUnit timeUnit) {
        this.client.getBucket(toStr(key)).set(value, timeToLive, timeUnit);
    }

    @Override
    public void del(K key) {
        this.client.getKeys().delete(toStr(key));
    }

    @Override
    public CompletableFuture<CacheItem<K, V>> get(K key) {
        RBucket<V> rBucket = this.client.getBucket(toStr(key));
        CompletableFuture<CacheItem<K, V>> result = new CompletableFuture<>();
        rBucket.getAsync().addListener(future -> {
            if (future.isSuccess()) {
                result.complete(CacheItem.apply(key, (V) future.getNow()));
            } else {
                result.completeExceptionally(future.cause());
            }
        });
        return result;
    }


    @Override
    public CompletableFuture<CacheItem<K, V>> get(K key, Function<K, V> loader) {
        RBucket<V> rBucket = this.client.getBucket(toStr(key));
        CompletableFuture<CacheItem<K, V>> result = new CompletableFuture<>();
        rBucket.getAsync().addListener(future -> {
            if (future.isSuccess()) {
                if (future.getNow() != null) {
                    result.complete(CacheItem.apply(key, (V) future.getNow()));
                } else {
                    loaderExecutorService.execute(() -> {
                        V v = loader.apply(key);
                        result.complete(CacheItem.apply(key,v));
                        if (v != null) {
                            rBucket.set(v);
                        }
                    });
                }
            } else {
                result.completeExceptionally(future.cause());
            }
        });
        return result;
    }

    @Override
    public CompletableFuture<CacheItems<K, V>> get(Collection<K> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return CompletableFuture.completedFuture(CacheItems.apply());
        }
        if (keys.size() == 1) {
            K key = keys.iterator().next();
            return get(key).thenApply(cacheItem -> CacheItems.apply(cacheItem));
        }
        CompletableFuture<CacheItems<K, V>> result = new CompletableFuture<>();
        List<CompletableFuture<CacheItem<K, V>>> completableFutures = keys.stream().map(this::get).collect(toList());
        CompletableFuture<Void> allCompletableFuture = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));
        allCompletableFuture.thenAccept(action -> {
            List<CacheItem<K, V>> items = completableFutures.stream().map(future -> future.getNow(null)).collect(toList());
            result.complete(CacheItems.apply(items));
        });
        return result;
    }

    @Override
    public CompletableFuture<CacheItems<K, V>> get(Collection<K> keys, Function<K, V> loader) {
        if (CollectionUtils.isEmpty(keys)) {
            return CompletableFuture.completedFuture(CacheItems.apply());
        }
        if (keys.size() == 1) {
            K key = keys.iterator().next();
            return get(key, loader).thenApply(cacheItem -> CacheItems.apply(cacheItem));
        }
        CompletableFuture<CacheItems<K, V>> result = new CompletableFuture<>();
        List<CompletableFuture<CacheItem<K, V>>> completableFutures = keys
                .stream()
                .map(key -> get(key, loader))
                .collect(toList());
        CompletableFuture<Void> allCompletableFuture = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));
        allCompletableFuture.thenAccept(action -> {
            List<CacheItem<K, V>> items = completableFutures.stream().map(future -> future.getNow(null)).collect(toList());
            result.complete(CacheItems.apply(items));
        });
        return result;
    }
}
