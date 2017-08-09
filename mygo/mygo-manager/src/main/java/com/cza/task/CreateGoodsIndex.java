
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
import com.cza.common.log.LogUtil;
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
public class CreateGoodsIndex  extends BaseTask{
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsIndexService goodsIndexService;
	
	public Long invoke(){
		Long number=0l;
		GoodsVo goods=new GoodsVo();
		goods.setPageSize(100);
		goods.setGoodsIndex(ShoppingContants.GOODS_INDEX_WAIT_CREATE);
		goods.setStatus(ShoppingContants.GOODS_STATUS_ON);
		while(true){
			log.info("listGoods param:{}",goods);
			ServiceResponse<Pager<GoodsVo>> resp=goodsService.listGoods(goods);
			if(resp.isSuccess()){
				log.info("listGoods success,result:{}",resp.getData());
				List<GoodsVo> voList=resp.getData().getResult();
				List<GoodsIndexVo> indexList=new ArrayList<>();
				Map<Long,Integer> gids=new HashMap<>();
				if(voList!=null&&voList.size()>0){
					for(GoodsVo vo:voList){
						GoodsIndexVo index=new GoodsIndexVo();
						BeanUtils.copyProperties(vo, index);
						index.setSyncTime(System.currentTimeMillis());
						indexList.add(index);
						gids.put(index.getGid(),ShoppingContants.GOODS_INDEX_COMPLETE);
					}
				}else{
					break;
				}
				number+=indexList.size();
				goodsIndexService.createIndex(indexList);
				goodsService.batchUpdateGoodsIndex(gids);
			}else{
				log.info("listGoods has erro,respCode:{}",resp.getCode());
			}
		}
		return number;
	}
}
