/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.atguigu.test
 *
 *    Filename:    JedisPoolUtils.java
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
 *    Create at:   2017年9月26日 下午6:55:54
 *
 *    Revision:
 *
 *    2017年9月26日 下午6:55:54
 *        - first revision
 *
 *****************************************************************/
package com.atguigu.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月26日 下午6:55:54
 */
public class JedisPoolUtils {
    
    private static volatile JedisPool jedisPool = null;
    
    private JedisPoolUtils(){}
    
    public static JedisPool getInstance(){
        
        if(jedisPool == null){
            synchronized (JedisPoolUtils.class) {
                if(jedisPool == null){
                    JedisPoolConfig poolconfig = new JedisPoolConfig();
                    poolconfig.setMaxActive(100); //最大redis连接数
                    poolconfig.setMaxIdle(32); //redis系统占用连接数
                    poolconfig.setMaxWait(100 * 1000); //连接最长等待时间100秒
                    poolconfig.setTestOnBorrow(true); //ping pong测试一下
                    
                    jedisPool = new JedisPool(poolconfig,"192.168.72.132",6379);
                }
            }
        }
        return jedisPool;
    }

    public static void release(JedisPool jedisPool,Jedis jedis){
        if(null != jedis){
            jedisPool.returnResourceObject(jedis);
        }
    }
}
