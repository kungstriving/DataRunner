package main;

import java.util.ResourceBundle;

import org.apache.commons.lang.math.NumberUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import com.everhope.nost.datastore.StoreConstants;

/**
 * 
 * @author kongxiaoyang
 * @date 2014年4月13日 下午12:16:06 
 * @version V1.0 
 */
public class PubsubMain {

	public static void main(String[] args) {
		Jedis jedis = null;
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(20);
		JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6678);
		try {
			jedis = jedisPool.getResource();
			jedis.auth("nost");
			
			jedis.subscribe(new JedisPubSub() {
				
				@Override
				public void onUnsubscribe(String arg0, int arg1) {
					// TODO Auto-generated method stub
					System.out.println("onUnsubscribe");
				}
				
				@Override
				public void onSubscribe(String arg0, int arg1) {
					// TODO Auto-generated method stub
					System.out.println("onSubscribe");
				}
				
				@Override
				public void onPUnsubscribe(String arg0, int arg1) {
					// TODO Auto-generated method stub
					System.out.println("onPUnsubscribe");
				}
				
				@Override
				public void onPSubscribe(String arg0, int arg1) {
					// TODO Auto-generated method stub
					System.out.println("onPSubscribe");
				}
				
				@Override
				public void onPMessage(String arg0, String arg1, String arg2) {
					// TODO Auto-generated method stub
					System.out.println("onPMessage");
				}
				
				@Override
				public void onMessage(String arg0, String arg1) {
					// TODO Auto-generated method stub
					System.out.println("onMessage");
					this.unsubscribe();
				}
			}, "testchannel");
			
			System.out.println("goon");
			
			jedis.publish("testchannel", "hello");
		} catch (Exception e) {
			throw e;
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
}
