package com.hbfangrui.base.infra.redis.cache;


import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.codec.KryoCodec;

/**
 * Created by tao.li on 2015/10/16.
 */
public class Config {
    public RedissonClient redissonClient(){
        org.redisson.Config config = new org.redisson.Config();
        config.useSingleServer().setAddress("127.0.0.1:6379")
                .setSubscriptionConnectionPoolSize(50)
                .setSubscriptionsPerConnection(10000)
                .setConnectionPoolSize(500);
        config.setCodec(new KryoCodec());
        return Redisson.create(config);
    }
}
