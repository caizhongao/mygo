
    /**  
    * @Title: IndexServiceImpl.java
    * @Package com.cza.service.goods.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年6月27日下午4:25:26
    * @version V1.0  
    */
    
package com.cza.service.goods.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cza.common.ElasticSearchUitl;
import com.cza.common.Pager;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.service.goods.GoodsIndexService;
import com.cza.service.goods.vo.GoodsIndexVo;
import com.cza.service.goods.vo.GoodsVo;

/**
    * @ClassName: IndexServiceImpl
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年6月27日下午4:25:26
    *
    */
@Service("goodsIndexService")
public class GoodsIndexServiceImpl implements GoodsIndexService {
	private static final Logger log = LoggerFactory.getLogger(GoodsIndexServiceImpl.class); 
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param goodsList
	    * @see com.cza.service.goods.GoodsIndexService#createIndex(java.util.List)
	    */
	    
	@Override
	public void createIndex(List<GoodsIndexVo> goodsList) {
			long startTime=System.currentTimeMillis();
			try {
				Client client=ElasticSearchUitl.getClient();
				for (int i = 0; i < goodsList.size(); i++) {
					String jsonObj=JSONObject.toJSONString(goodsList.get(i));
				    IndexResponse response = client.prepareIndex("mygo", "goods",goodsList.get(i).getGid().toString()).setSource(jsonObj).get();
				    if (response.isCreated()) {
				    	log.info("createIndex success,goodsId:{}",goodsList.get(i).getGid());
				    }else{
				    	log.info("createIndex failed,goodsId:{}",goodsList.get(i).getGid());
				    }
				}
				client.close();
			} catch (Exception e) {
				log.error("createIndex index erro:",e);
			}
			log.info("GoodsIndexService.createIndex cost time:{}",System.currentTimeMillis()-startTime);
	}

		
		    /* (非 Javadoc)
		    * 
		    * 
		    * @param goods
		    * @return
		    * @see com.cza.service.goods.GoodsIndexService#search(com.cza.service.goods.vo.GoodsVo)
		    */
		    
		@Override
		public ServiceResponse<Pager<GoodsVo>> search(GoodsVo goods) {
			ServiceResponse<Pager<GoodsVo>> resp=new ServiceResponse<>();
			List<GoodsVo>voList=new ArrayList<>();
			Client client=ElasticSearchUitl.getClient();
			
			Long count= client.prepareCount("mygo")
					.setTypes("goods")
			        .setQuery(QueryBuilders.queryStringQuery(goods.getSearchKey()))
			        .execute()
			        .actionGet().getCount();
			
			SearchResponse response = client.prepareSearch("mygo")
			        .setTypes("goods")
			        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			        .setQuery(QueryBuilders.queryStringQuery(goods.getSearchKey()))              // Query
			        //.setPostFilter(QueryBuilders.rangeQuery("goodsName").from(12).to(18))     // Filter
			        .setFrom(goods.getStart()).setSize(goods.getPageSize()).setExplain(true)
			        .execute()
			        .actionGet();
			for(SearchHit hit:response.getHits().getHits()){
				Map<String,Object> map=hit.getSource();
				GoodsVo vo=new GoodsVo();
				vo.setCategoryName(map.get("categoryName").toString());
				vo.setCid(new Long(map.get("cid").toString()) );
				vo.setGoodsName(map.get("goodsName").toString());
				vo.setGoodsPic(map.get("goodsPic").toString());
				vo.setPrice(new BigDecimal(map.get("price").toString()) );
				vo.setGid(new Long(map.get("gid").toString()));
				voList.add(vo);
			}
			resp.setData(new Pager<>(goods.getPageSize(), goods.getPageNum(), count, voList));
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			return resp;
		}

}
