
    /**  
    * @Title: UserMapper.java
    * @Package com.cza.mapper.user
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    * @version V1.0  
    */
    
package com.cza.mapper.goods;

import java.util.List;

import com.cza.dto.goods.TGoods;
import com.cza.service.goods.vo.GoodsVo;

/**
    * @ClassName: UserMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    *
    */

public interface GoodsMapper {
	
	public List<TGoods> listGoods(GoodsVo param);
	
	public TGoods queryGoods(Long gid);
	
	public void saveGoods(TGoods goods);
	
	public void updateGoods(TGoods goods);

	
	    /**
	     * @param goods 
	    * @Title: listNewGoods
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @return    参数
	    * @return List<TGoods>    返回类型
	    * @throws
	    */
	    
	public List<TGoods> listNewGoods(GoodsVo goods);

		
		    /**
		     * @param goods 
		    * @Title: listHotGoods
		    * @Description: TODO(这里用一句话描述这个方法的作用)
		    * @param @return    参数
		    * @return List<TGoods>    返回类型
		    * @throws
		    */
		    
		public List<TGoods> listHotGoods(GoodsVo goods);

			
			    /**
			    * @Title: batchUpdateGoodsIndex
			    * @Description: TODO(这里用一句话描述这个方法的作用)
			    * @param @param gids    参数
			    * @return void    返回类型
			    * @throws
			    */
			    
			public void batchUpdateGoodsIndex(List<Long> gids);

				
				    /**
				    * @Title: countGoods
				    * @Description: TODO(这里用一句话描述这个方法的作用)
				    * @param @param listParam
				    * @param @return    参数
				    * @return Long    返回类型
				    * @throws
				    */
				    
				public Long countGoods(GoodsVo listParam);
}
