
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
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
		Client client=ElasticSearchUitl.getClient();
		for (GoodsIndexVo index:goodsList) {
			try {
				String jsonObj=JSONObject.toJSONString(index);
			    IndexResponse response = client.prepareIndex("mygo", "goods",index.getGid().toString()).setSource(jsonObj).get();
			    if (response.isCreated()) {
			    	log.info("createIndex success,goodsId:{}",index.getGid());
			    }else{
			    	log.info("createIndex failed,goodsId:{}",index.getGid());
			    }
			} catch (Exception e) {
				log.error("createIndex index gid:{}, erro:",index.getGid(),e);
			}
		}
		log.info("GoodsIndexService.createIndex cost time:{}",System.currentTimeMillis()-startTime);
	}

		
	    /**
	    * @Title: search
	    * @Description: from，size分页，性能影响，如果from过大，会造成查询过多数据量
	    * @param @param goods
	    * @param @return    参数
	    * @return ServiceResponse<Pager<GoodsVo>>    返回类型
	    * @throws
	    */
		@Override
		public ServiceResponse<Pager<GoodsVo>> search(GoodsVo goods) {
			if(goods.getSearchKey()==null){
				return null;
			}
			String searchKey=goods.getSearchKey();
			ServiceResponse<Pager<GoodsVo>> resp=new ServiceResponse<>();
			List<GoodsVo>voList=new ArrayList<>();
			Client client=ElasticSearchUitl.getClient();
			Long count= client.prepareCount("mygo")
					.setTypes("goods")
			        .setQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("goodsName", searchKey)).should(QueryBuilders.matchQuery("categoryName", searchKey)))
			        .execute()
			        .actionGet().getCount();
			
			SearchResponse response = client.prepareSearch("mygo")
			        .setTypes("goods")
			        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			        .setQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("goodsName", searchKey))
			        								   .should(QueryBuilders.matchQuery("categoryName", searchKey)))
			        .setFrom((goods.getPageNum()-1)*goods.getPageSize()).setSize(goods.getPageSize())
			        .setExplain(true)
			        .addHighlightedField("goodsName")
			        .setHighlighterPreTags("<font style=\"color:red\">")
			        .setHighlighterPostTags("</font>")
			        .execute()
			        .actionGet();
			for(SearchHit hit:response.getHits().getHits()){
				Map<String,Object> map=hit.getSource();
				GoodsVo vo=new GoodsVo();
				vo.setGoodsName(map.get("goodsName").toString());
				Map<String, HighlightField>hfs= hit.getHighlightFields();
				if(hfs!=null){
					HighlightField field=hfs.get("goodsName");
					if(field!=null){
						String goodsName="";
						for(Text text :field.fragments()){
							goodsName+=text;
						}
						vo.setGoodsName(goodsName);
					}
				}
				vo.setCategoryName(map.get("categoryName").toString());
				vo.setCid(new Long(map.get("cid").toString()) );
				vo.setGoodsPic(map.get("goodsPic").toString());
				vo.setPrice(new BigDecimal(map.get("price").toString()) );
				vo.setGid(new Long(map.get("gid").toString()));
				log.info("query goodsname:{}",vo.getGoodsName());
				voList.add(vo);
			}
			resp.setData(new Pager<>(goods.getPageSize(), goods.getPageNum(), count, voList));
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			return resp;
		}
		
		
		
		
	    /**
	    * @Title: scrollSearch
	    * @Description: scroll方式分页，这种分页更高效
	    * @param @param goods
	    * @param @return    参数
	    * @return ServiceResponse<Pager<GoodsVo>>    返回类型
	    * @throws
	    */
		@Override
		public ServiceResponse<Pager<GoodsVo>> scrollSearch(GoodsVo goods) {
			if(goods.getSearchKey()==null){
				return null;
			}
			//如果已经有scrollId，则直接根据scrollId来
			String scrollId=goods.getScrollId();
			Integer scrollPage=goods.getScrollPage();//跳转之前页码
			Long count=goods.getCount();
			Integer pageNum=goods.getPageNum();//要跳转页码
			String searchKey=goods.getSearchKey();//搜索关键字
			SearchResponse response=null;
			Client client=ElasticSearchUitl.getClient();
			//如果scrollId为空或者scrollPage>pageNum，需要重新初始化scrollId
			if(StringUtils.isEmpty(scrollId)||scrollPage>pageNum){
				response = client.prepareSearch("mygo")
				        .setTypes("goods")
				        .setScroll(new TimeValue(60000))
				        .setSearchType(SearchType.SCAN)
				        .setQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("goodsName", searchKey)).should(QueryBuilders.matchQuery("categoryName", searchKey)))
				        .setExplain(true)
				        .setSize(goods.getPageSize()/5)
				        .addHighlightedField("goodsName")
				        .setHighlighterPreTags("<font style=\"color:red\">")
				        .setHighlighterPostTags("</font>")
				        .execute()
				        .actionGet();
				scrollPage=0;
				scrollId=response.getScrollId();
				count=response.getHits().getTotalHits();
				
			}
			ServiceResponse<Pager<GoodsVo>> resp=new ServiceResponse<>();
			List<GoodsVo>voList=new ArrayList<>();
			//得到对应页码的reponse对象
			for(;scrollPage<pageNum;scrollPage++){
				response=client.prepareSearchScroll(scrollId).setScroll(new TimeValue(60000)).execute().actionGet();
			}
			for(SearchHit hit:response.getHits().getHits()){
				Map<String,Object> map=hit.getSource();
				GoodsVo vo=new GoodsVo();
				vo.setGoodsName(map.get("goodsName").toString());
				Map<String, HighlightField>hfs= hit.getHighlightFields();
				if(hfs!=null){
					HighlightField field=hfs.get("goodsName");
					if(field!=null){
						String goodsName="";
						for(Text text :field.fragments()){
							goodsName+=text;
						}
						vo.setGoodsName(goodsName);
					}
				}
				vo.setCategoryName(map.get("categoryName").toString());
				vo.setCid(new Long(map.get("cid").toString()) );
				vo.setGoodsPic(map.get("goodsPic").toString());
				vo.setPrice(new BigDecimal(map.get("price").toString()) );
				vo.setGid(new Long(map.get("gid").toString()));
				log.info("query goodsname:{}",vo.getGoodsName());
				voList.add(vo);
			}
			Pager<GoodsVo> pager=new Pager<>(goods.getPageSize(), goods.getPageNum(), count, voList);
			//设置scroll相关信息
			pager.setScrollId(scrollId);
			pager.setScrollPage(pageNum);
			resp.setData(pager);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			return resp;
		}


		@Override
		public void updateIndex(List<GoodsIndexVo> indexList) {
			long startTime=System.currentTimeMillis();
			Client client=ElasticSearchUitl.getClient();
			for (GoodsIndexVo index:indexList) {
				try {
					String jsonObj=JSONObject.toJSONString(index);
					UpdateRequest updateRequest = new UpdateRequest();
					updateRequest.index("mygo").type("goods").id(index.getGid().toString()).doc(jsonObj);
					client.update(updateRequest).get();
				} catch (Exception e) {
					log.error("updateIndex index gid:{}, erro:",index.getGid(),e);
				}
			}
			log.info("GoodsIndexService.updateIndex cost time:{}",System.currentTimeMillis()-startTime);
		}
		
		
		public void deleteIndex(List<GoodsIndexVo> indexList){
			long startTime=System.currentTimeMillis();
			Client client=ElasticSearchUitl.getClient();
			for (GoodsIndexVo index:indexList) {
				try {
					DeleteResponse response = client.prepareDelete("mygo", "goods",index.getGid().toString()).get();
				    if (response.isFound()) {
				    	log.info("deleteIndex success,goodsId:{}",index.getGid());
				    }else{
				    	log.info("deleteIndex failed,goodsId:{}",index.getGid());
				    }
				} catch (Exception e) {
					log.error("deleteIndex index gid:{}, erro:",index.getGid(),e);
				}
			}
			log.info("GoodsIndexService.deleteIndex cost time:{}",System.currentTimeMillis()-startTime);
		
		}
		
}
