package com.hbfangrui.base.infra.redis.cache;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by tao.li on 2015/11/2.
 */
public class RedissonBasedCacheServiceTest {
    private CacheService<String, String> cacheService = new RedissonBasedCacheService<>(new Config().redissonClient(), Executors.newFixedThreadPool(10));
    private List<CacheItem<String, String>> cacheData = createCacheData();

    private List<CacheItem<String, String>> createCacheData() {
        Set<CacheItem<String, String>> data = Sets.newHashSet();
        Random random = new Random();
        for (int i = 0; i < 5000; i++) {
            String key = String.valueOf(Math.abs(random.nextInt()));
            String value = String.valueOf(random.nextInt());
            data.add(CacheItem.apply(key, value));
        }
        return new ArrayList<>(data);
    }

    @Before
    public void setUp() throws Exception {
        this.cacheData.forEach(cacheItem -> {
            cacheService.set(cacheItem.getKey(), cacheItem.getValue());
        });
    }

    @After
    public void tearDown() throws Exception {
        this.cacheData.forEach(cacheItem -> {
            cacheService.del(cacheItem.getKey());
        });
    }

    @Test
    public void testGet() throws Exception {
        this.cacheData.forEach(cacheItem -> {
            try {
                CacheItem<String, String> cacheData = cacheService.get(cacheItem.getKey()).get();
                assertEquals(cacheItem.getKey(), cacheData.getKey());
                assertEquals(cacheItem.getValue(), cacheData.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        });
    }

    @Test
    public void testSet1() throws Exception {
        this.cacheData.forEach(cacheItem -> {
            cacheService.set(cacheItem.getKey(), cacheItem.getValue(), 10, TimeUnit.SECONDS);
        });
        TimeUnit.SECONDS.sleep(10);
        this.cacheData.forEach(cacheItem -> {
            try {
                CacheItem<String, String> cacheData = cacheService.get(cacheItem.getKey()).get();
                assertEquals(cacheItem.getKey(), cacheData.getKey());
                assertFalse(cacheData.isCached());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }


    @Test
    public void testGet1() throws Exception {

        for (int i = 0; i < 100; i++) {
            List<CacheItem<String, String>> testData = getTestData();
            List<String> keys = testData.stream().map(item -> item.getKey()).collect(toList());
            CompletableFuture<CacheItems<String, String>> future = cacheService.get(keys);
            CacheItems<String, String> cacheItems = null;
            try {
                cacheItems = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            final CacheItems<String, String> fcacheItems = cacheItems;
            testData.forEach(item -> {
                assertEquals(item.getValue(), fcacheItems.getCacheItem(item.getKey()).getValue());
            });
        }
    }

    private List<CacheItem<String, String>> getTestData() {
        List<CacheItem<String, String>> result = Lists.newArrayList();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int index = Math.abs(random.nextInt()) % this.cacheData.size();
            result.add(this.cacheData.get(index));
        }
        return result;
    }

    @Test
    public void testGetAndLoad() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            String key = "get-load-key" + i;
            String value = key + "value";
            {
                CompletableFuture<CacheItem<String, String>> completableFuture = this.cacheService.get(key, k -> value);
                CacheItem<String, String> cacheItem = completableFuture.get();
                assertEquals(key, cacheItem.getKey());
                assertEquals(value, cacheItem.getValue());
            }
            TimeUnit.MILLISECONDS.sleep(5);
            {
                CompletableFuture<CacheItem<String, String>> completableFuture = this.cacheService.get(key, k -> {
                    throw new RuntimeException();
                });
                CacheItem<String, String> cacheItem = completableFuture.get();
                assertEquals(key, cacheItem.getKey());
                assertEquals(value, cacheItem.getValue());
            }
        }
    }

    @Test
    public void testBatchGetAndLoad() throws InterruptedException, ExecutionException {
        for (int i = 0; i < 200; i++) {
            List<String> keys = createRandomKeys();
            {
                CompletableFuture<CacheItems<String, String>> future = cacheService.get(keys, k -> k + k);
                CacheItems<String, String> cacheItems = future.get();
                keys.forEach(k -> {
                    assertEquals(k + k, cacheItems.getCacheItem(k).getValue());
                });
            }
            TimeUnit.MILLISECONDS.sleep(5);
            {
                CompletableFuture<CacheItems<String, String>> future = cacheService.get(keys, k -> {throw new RuntimeException();});
                CacheItems<String, String> cacheItems = future.get();
                keys.forEach(k -> {
                    assertEquals(k + k, cacheItems.getCacheItem(k).getValue());
                });
            }
        }
    }

    private List<String> createRandomKeys() {
        List<String> keys = Lists.newArrayList();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            keys.add("batch-get-load" + String.valueOf(random.nextInt()));
        }
        return keys;
    }
}