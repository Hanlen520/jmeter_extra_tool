package com.tomoya.jmeter;

import redis.clients.jedis.Jedis;  
import redis.clients.jedis.JedisPool;  
import redis.clients.jedis.JedisPoolConfig;  

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**    
 * Redis操作接口 
 */  
public class Redis {
	
	public static String host;
	public static int port;
	public static String auth;
	
	/**
	 * 空构造函数，配合getRedisZBX();方法使用
	 */
	public Redis() {
//		this.host = "192.168.1.105";
//		this.port = 6379;
//		this.auth = "qk365.com";
	}
	
	/**
	 * 地址+端口号构造函数
	 * @param host IP地址
	 * @param port 端口号
	 */
	public Redis(String host, int port) {
		this.host = host;
		this.port = port;
			
	}
	
	/**
	 * 地址+端口号+登录名构造函数
	 * @param host IP地址
	 * @param port 端口号
	 * @param auth 登录名
	 */
	public Redis(String host, int port, String auth) {
		this(host, port);
		this.auth = auth;
		
	}
	
    /**
     * 通过key值返回Redis中value全部内容
     * @param redis_key key值
     * @return value全部内容
     */
    public String getRedis(String redis_key){
    	return getRedis(redis_key, "");
    	
    }	
    
    /**
     * 通过key值返回Redis中vlaue匹配正则的部分
     * @param redis_key key值
     * @param redis_regex 正则表达式
     * @return vlaue匹配正则的部分
     */
    public String getRedis(String redis_key, String redis_regex){
    	String string=null;
    	String value=get(redis_key);
    	if (value != null) {
    		string = value;
    		String regex = redis_regex;
    		if (regex != null && regex != "") {
    			string = getRegex(value, regex);
    			
    		}
    	}
    	return string;
    	
    }	

    /**
     * 坐标系特有的方法，传入短信类型和手机号，返回SmsCode值。
     * @param type 短信类型
     * @param mobile 手机号
     * @return SmsCode值
     */
    public String getRedisZBX(int type, String mobile){
    	this.host = "192.168.1.105";
    	this.port = 6379;
    	this.auth = "qk365.com";
    	return getRedis("n:VerifyCode,c:VerifyCode:"+type+":"+mobile, "SmsCode\":\"(.+?)\",");
    }	
	
	
	
	private static JedisPool pool = null;  
    
    /** 
     * 构建redis连接池 
     *  
     * @param ip 
     * @param port 
     * @return JedisPool 
     */  
	private static JedisPool getPool() {  
    	
        if (pool == null) {  
            JedisPoolConfig config = new JedisPoolConfig();  
            config.setMaxActive(2);  
            config.setMaxIdle(100);  
            config.setMaxWait(5);  
            config.setTestOnBorrow(false);  
            pool = new JedisPool(config,host,port);  
        }  
        return pool;  
    }  
      
    /** 
     * 返还到连接池 
     *  
     * @param pool  
     * @param redis 
     */  
    private static void returnResource(JedisPool pool, Jedis redis) {  
        if (redis != null) {  
            pool.returnResource(redis);  
        }  
    }  
      
    /** 
     * 获取数据 
     *  
     * @param key 
     * @return 
     */  
    private static String get(String key){  
        String value = null;  
          
        JedisPool pool = null;  
        Jedis jedis = null;  
        try {  
            pool = getPool();  
            jedis = pool.getResource();
            if (auth != null && auth != "") {
            	jedis.auth(auth);
            }
            value = jedis.get(key);  
        } catch (Exception e) {  
            pool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            returnResource(pool, jedis);  
        }  
        return value;  
    }  
   
    
    private static String getRegex(String soures, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(soures);
		String string = null;
		while (matcher.find()) {
			string = matcher.group(1);
		}
		return string;				
    	
    }
    
    
}
