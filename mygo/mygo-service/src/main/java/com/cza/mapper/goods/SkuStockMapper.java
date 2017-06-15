
    /**  
    * @Title: UserMapper.java
    * @Package com.cza.mapper.user
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    * @version V1.0  
    */
    
package com.cza.mapper.goods;


import com.cza.dto.goods.TSkuStock;

/**
    * @ClassName: UserMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    *
    */

public interface SkuStockMapper {
	
	public TSkuStock querySkuStock(TSkuStock skuStock);
	
	public void saveSkuStock(TSkuStock skuStock);
	
	public void updateSkuStock(TSkuStock skuStock);
	
	public int reduceSkuStock(TSkuStock skuStock);

}
