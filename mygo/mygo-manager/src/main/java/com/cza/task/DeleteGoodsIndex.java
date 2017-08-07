
    /**  
    * @Title: GoodsAddIndex.java
    * @Package com.cza.web.task
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年6月27日下午4:03:24
    * @version V1.0  
    */
    
package com.cza.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cza.common.Pager;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.common.traceLog.TraceIdUtil;
import com.cza.service.goods.GoodsIndexService;
import com.cza.service.goods.GoodsService;
import com.cza.service.goods.vo.GoodsIndexVo;
import com.cza.service.goods.vo.GoodsVo;

/**
    * @ClassName: GoodsAddIndex
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年6月27日下午4:03:24
    *
    */
@Component
public class DeleteGoodsIndex {
	private static final Logger log = LoggerFactory.getLogger(DeleteGoodsIndex.class); 
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsIndexService goodsIndexService;
	
	public void execute(){
		TraceIdUtil.makeTraceId();
		log.info("DeleteGoodsIndex.execute start!");
		long startTime=System.currentTimeMillis();
		GoodsVo goods=new GoodsVo();
		goods.setPageSize(100);
		goods.setGoodsIndex(ShoppingContants.GOODS_INDEX_WAIT_DELETE);
		while(true){
			ServiceResponse<Pager<GoodsVo>> resp=goodsService.listGoods(goods);
			if(resp.isSuccess()){
				List<GoodsVo> voList=resp.getData().getResult();
				List<GoodsIndexVo> indexList=new ArrayList<>();
				Map<Long,Integer> gids=new HashMap<>();
				if(voList!=null&&voList.size()>0){
					for(GoodsVo vo:voList){
						GoodsIndexVo index=new GoodsIndexVo();
						BeanUtils.copyProperties(vo, index);
						indexList.add(index);
						gids.put(index.getGid(),ShoppingContants.GOODS_INDEX_HAS_DELETE);
					}
				}else{
					break;
				}
				goodsIndexService.deleteIndex(indexList);
				goodsService.batchUpdateGoodsIndex(gids);
			}else{
				log.info("DeleteGoodsIndex.execute query goods param:{},erro:{}",goods,resp.getCode());
				break;
			}
		}
		log.info("DeleteGoodsIndex.execute cost time:{}",System.currentTimeMillis()-startTime);
	}
}
