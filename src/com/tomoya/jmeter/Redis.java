package com.tomoya.jmeter;

import redis.clients.jedis.Jedis;  
import redis.clients.jedis.JedisPool;  
import redis.clients.jedis.JedisPoolConfig;  

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**    
 * Redis�����ӿ� 
 */  
public class Redis {
	
	public static String host;
	public static int port;
	public static String auth;
	
	/**
	 * �չ��캯�������getRedisZBX();����ʹ��
	 */
	public Redis() {
//		this.host = "192.168.1.105";
//		this.port = 6379;
//		this.auth = "qk365.com";
	}
	
	/**
	 * ��ַ+�˿ںŹ��캯��
	 * @param host IP��ַ
	 * @param port �˿ں�
	 */
	public Redis(String host, int port) {
		this.host = host;
		this.port = port;
			
	}
	
	/**
	 * ��ַ+�˿ں�+��¼�����캯��
	 * @param host IP��ַ
	 * @param port �˿ں�
	 * @param auth ��¼��
	 */
	public Redis(String host, int port, String auth) {
		this(host, port);
		this.auth = auth;
		
	}
	
    /**
     * ͨ��keyֵ����Redis��valueȫ������
     * @param redis_key keyֵ
     * @return valueȫ������
     */
    public String getRedis(String redis_key){
    	return getRedis(redis_key, "");
    	
    }	
    
    /**
     * ͨ��keyֵ����Redis��vlaueƥ������Ĳ���
     * @param redis_key keyֵ
     * @param redis_regex ������ʽ
     * @return vlaueƥ������Ĳ���
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
     * ����ϵ���еķ���������������ͺ��ֻ��ţ�����SmsCodeֵ��
     * @param type ��������
     * @param mobile �ֻ���
     * @return SmsCodeֵ
     */
    public String getRedisZBX(int type, String mobile){
    	this.host = "192.168.1.105";
    	this.port = 6379;
    	this.auth = "qk365.com";
    	return getRedis("n:VerifyCode,c:VerifyCode:"+type+":"+mobile, "SmsCode\":\"(.+?)\",");
    }	
	
	
	
	private static JedisPool pool = null;  
    
    /** 
     * ����redis���ӳ� 
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
     * ���������ӳ� 
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
     * ��ȡ���� 
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
