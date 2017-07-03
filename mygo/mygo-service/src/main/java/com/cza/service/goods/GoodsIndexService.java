
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
	    * @Description: from，size分页，性能影响，如果from过大，会造成查询过多数据量
	    * @param @param goods
	    * @param @return    参数
	    * @return ServiceResponse<Pager<GoodsVo>>    返回类型
	    * @throws
	    */
	    
	public ServiceResponse<Pager<GoodsVo>> search(GoodsVo goods);
	
	
    /**
    * @Title: scrollSearch
    * @Description: scroll方式分页，这种分页更高效
    * @param @param goods
    * @param @return    参数
    * @return ServiceResponse<Pager<GoodsVo>>    返回类型
    * @throws
    */
    
public ServiceResponse<Pager<GoodsVo>> scrollSearch(GoodsVo goods);


	
	    /**
	    * @Title: updateIndex
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param indexList    参数
	    * @return void    返回类型
	    * @throws
	    */
	    
	public void updateIndex(List<GoodsIndexVo> indexList);


		
		    /**
		    * @Title: deleteIndex
		    * @Description: TODO(这里用一句话描述这个方法的作用)
		    * @param @param indexList    参数
		    * @return void    返回类型
		    * @throws
		    */
		    
		public void deleteIndex(List<GoodsIndexVo> indexList);
}
