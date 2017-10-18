/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.atguigu.test
 *
 *    Filename:    JedisPoolTest.java
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
 *    Create at:   2017年9月26日 下午7:14:00
 *
 *    Revision:
 *
 *    2017年9月26日 下午7:14:00
 *        - first revision
 *
 *****************************************************************/
package com.atguigu.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月26日 下午7:14:00
 */
public class JedisPoolTest {
    
    @Test
    public void test(){
        /*JedisPool pool1 = JedisPoolUtils.getInstance();
        JedisPool pool2 = JedisPoolUtils.getInstance();
        System.out.println(pool1 == pool2);*/
        
        JedisPool pool = JedisPoolUtils.getInstance();
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.set("class0515", "nbv5");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JedisPoolUtils.release(pool, jedis);
        }
    }
}
