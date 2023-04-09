package com.fs.middlelink.utils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

@TestConfiguration
public class RedisTestConfig {

    private RedisServer redisServer;

    public RedisTestConfig(RedisProperties redisProperties) {
        this.redisServer = new RedisServer(redisProperties.getRedisPort());

//        Uncomment below if running on Windows and can't start redis server
//        this.redisServer = RedisServer.builder().setting("maxheap 200m").port(6379).setting("bind localhost").build();
//        redisServer.start();
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
