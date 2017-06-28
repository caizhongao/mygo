
    /**  
    * @Title: IndexService.java
    * @Package com.cza.service.goods
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年6月27日下午4:25:11
    * @version V1.0  
    */
    
package com.cza.service.goods;

import java.util.List;

import com.cza.common.Pager;
import com.cza.common.ServiceResponse;
import com.cza.service.goods.vo.GoodsIndexVo;
import com.cza.service.goods.vo.GoodsVo;

/**
    * @ClassName: IndexService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年6月27日下午4:25:11
    *
    */

public interface GoodsIndexService {
	public void createIndex(List<GoodsIndexVo> goodsList);

	
	    /**
	    * @Title: search
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param goods
	    * @param @return    参数
	    * @return ServiceResponse<Pager<GoodsVo>>    返回类型
	    * @throws
	    */
	    
	public ServiceResponse<Pager<GoodsVo>> search(GoodsVo goods);
}
