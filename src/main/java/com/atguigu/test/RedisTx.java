/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.atguigu.test
 *
 *    Filename:    RedisTx.java
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
 *    Create at:   2017年9月26日 下午3:02:50
 *
 *    Revision:
 *
 *    2017年9月26日 下午3:02:50
 *        - first revision
 *
 *****************************************************************/
package com.atguigu.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;


/**
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月26日 下午3:02:50
 */
public class RedisTx {
    
    public boolean transMethod() {
        Jedis jedis = new Jedis("192.168.72.132");
        int balance;//可用余额
        int debt;//欠款
        int amtToSubtract = 10;//实刷额度
        
        jedis.watch("balance");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance = Integer.parseInt(jedis.get("balance"));
        
        if(balance < amtToSubtract){
            jedis.unwatch();
            System.out.println("modify");
            return false;
        }else{
            System.out.println("**************transaction");
            
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance",amtToSubtract);
            transaction.incrBy("debt",amtToSubtract);
            transaction.exec();
            
            balance = Integer.parseInt(jedis.get("balance"));
            debt = Integer.parseInt(jedis.get("debt"));
            
            System.out.println("+++++++++++++++" + balance);
            System.out.println("+++++++++++++++" + debt);
            return true;
        }
    }
    
    public static void main(String[] args){
        RedisTx redisTx = new RedisTx();
        boolean result = redisTx.transMethod();
        System.out.println("main result--->" + result);
    }
    
//    @Test
    public void testMulti(){
        Jedis jedis = new Jedis("192.168.72.132",6379);
        Transaction transaction = jedis.multi();
        
        transaction.set("k1", "v1");
        transaction.set("k2", "v2");
        transaction.set("k3", "v3");
        transaction.set("k4", "v4444");
        
//        transaction.exec();
        transaction.discard();
    }

}
