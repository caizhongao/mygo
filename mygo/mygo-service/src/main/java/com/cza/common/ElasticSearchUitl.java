
    /**  
    * @Title: ElasticSearchUitl.java
    * @Package com.cza.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年6月28日下午2:30:32
    * @version V1.0  
    */
    
package com.cza.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
    * @ClassName: ElasticSearchUitl
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年6月28日下午2:30:32
    *
    */

public class ElasticSearchUitl {
	private static final Logger log = LoggerFactory.getLogger(ElasticSearchUitl.class); 
	
	private static Client client=null;
	private static Object lock=new Object();
	public static Client getClient(){
		if(client==null){
			synchronized (lock) {
				if(client==null){
					Long startTime=System.currentTimeMillis();
					Settings settings = Settings.settingsBuilder().put("cluster.name", "escluster").build();
					try {
						client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("120.26.165.53"), 9300));
					} catch (UnknownHostException e) {
						log.error("ElasticSearchUitl.getClient erro:",e);
					}
					log.info("init index client cost:{}",System.currentTimeMillis()-startTime);
				}
			}
		}
		return client;
		
	}
}
