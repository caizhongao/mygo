
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

/**
    * @ClassName: UserMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    *
    */

public interface GoodsMapper {
	
	public List<TGoods> listGoods(TGoods param);
	
	public TGoods queryGoods(Long gid);
	
	public void saveGoods(TGoods goods);
	
	public void updateGoods(TGoods goods);

	
	    /**
	    * @Title: listNewGoods
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @return    参数
	    * @return List<TGoods>    返回类型
	    * @throws
	    */
	    
	public List<TGoods> listNewGoods();

		
		    /**
		    * @Title: listHotGoods
		    * @Description: TODO(这里用一句话描述这个方法的作用)
		    * @param @return    参数
		    * @return List<TGoods>    返回类型
		    * @throws
		    */
		    
		public List<TGoods> listHotGoods();
}
