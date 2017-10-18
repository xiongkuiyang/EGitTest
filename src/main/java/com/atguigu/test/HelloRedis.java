/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.atguigu.test
 *
 *    Filename:    HelloRedis.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    Company:     Digital Telemedia Co.,Ltd
 *
 *    @author:     Administrator
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年9月25日 下午7:35:52
 *
 *    Revision:
 *
 *    2017年9月25日 下午7:35:52
 *        - first revision
 *
 *****************************************************************/
package com.atguigu.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class HelloRedis {
    Jedis jedis = new Jedis("192.168.72.132",6379);
    
    @Test
    public void testPingPong(){
        System.out.println("*********"+jedis.ping());
        jedis.set("k2", "v2");
    }
    
    @Test
    public void testKeyValue(){
        Set<String> keys = jedis.keys("*");
        /*Iterator<String> iterator = keys.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }*/
        /*for (String string : keys) {
            System.out.println(string);
        }*/
        for(Iterator iterator = keys.iterator();iterator.hasNext();){
            String key = (String) iterator.next();
            System.out.println(key);
        }
        
        System.out.println("jedis exist--->"+jedis.exists("k2"));
        System.out.println("jedis.ttl--->"+jedis.ttl("k2"));
    }
    
    @Test
    public void testString(){
//        jedis.append("k1", "v1");
        System.out.println(jedis.get("k1"));
        jedis.set("k4", "v4");
        System.out.println("---------------------------------------");
        jedis.mset("str1","s1","str2","s2","str3","s3");
        System.out.println(jedis.mget("str1","str2","str3"));
    }

    @Test
    public void testList(){
        jedis.lpush("list1", "v1","v2","v3","v4","v5");
        List<String> list = jedis.lrange("list1", 0, -1);
        for (String string : list) {
            System.out.println(string);
        }
    }
    
    @Test
    public void testSet(){
//        jedis.sadd("orders", "jd001");
//        jedis.sadd("orders","jd002");
//        jedis.sadd("orders", "jd003");
//        jedis.sadd("orders", "a","b","c","d","e");
        Set<String> smembers = jedis.smembers("orders");
//        for (String string : smembers) {
//            System.out.println(string);
              System.out.println(smembers);
//        }
        
        jedis.srem("orders", "jd002");
//        for (String string : smembers) {
//            System.out.println(string);
//        }
        System.out.println(smembers);
    }

    @Test
    public void testHash(){
//        jedis.hset("hash1", "userName", "tom");
//        System.out.println(jedis.hget("hash1","userName"));
        
//        Map<String, String> map = new HashMap<>();
//        map.put("age", "23");
//        map.put("tel", "8888-888");
//        map.put("email", "137885689@qq.com");
//        jedis.hmset("hash2", map);
        
        List<String> hmget = jedis.hmget("hash2", "age","email");
        System.out.println(hmget);
        
    }

    @Test
    public void testZset(){
        jedis.zadd("zset1", 60d,"v1");
        jedis.zadd("zset1", 70d,"v2");
        jedis.zadd("zset1", 80d,"v3");
        Set<String> zrange = jedis.zrange("zset1", 0, -1);
        for (String string : zrange) {
            System.out.println(string);
        }
        
    }
}
