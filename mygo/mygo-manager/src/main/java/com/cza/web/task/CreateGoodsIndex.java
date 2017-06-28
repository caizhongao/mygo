
    /**  
    * @Title: GoodsAddIndex.java
    * @Package com.cza.web.task
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年6月27日下午4:03:24
    * @version V1.0  
    */
    
package com.cza.web.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cza.common.Pager;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
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
public class CreateGoodsIndex {
	private static final Logger log = LoggerFactory.getLogger(CreateGoodsIndex.class); 
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsIndexService goodsIndexService;
	public void execute(){
		log.info("CreateGoodsIndex.execute start!");
		long startTime=System.currentTimeMillis();
		GoodsVo goods=new GoodsVo();
		goods.setPageSize(100);
		goods.setGoodsIndex(ShoppingContants.GOODS_INDEX_WAIT);
		while(true){
			ServiceResponse<Pager<GoodsVo>> resp=goodsService.listGoods(goods);
			if(resp.isSuccess()){
				List<GoodsVo> voList=resp.getData().getResult();
				List<GoodsIndexVo> indexList=new ArrayList<>();
				List<Long> gids=new ArrayList<>();
				if(voList!=null&&voList.size()>0){
					for(GoodsVo vo:voList){
						GoodsIndexVo index=new GoodsIndexVo();
						BeanUtils.copyProperties(vo, index);
						indexList.add(index);
						gids.add(index.getGid());
					}
				}else{
					break;
				}
				goodsIndexService.createIndex(indexList);
				goodsService.batchUpdateGoodsIndex(gids);
			}else{
				log.info("CreateGoodsIndex.execute query goods erro:{}",resp.getCode());
			}
		}
		log.info("CreateGoodsIndex.execute cost time:{}",System.currentTimeMillis()-startTime);
	}
}
