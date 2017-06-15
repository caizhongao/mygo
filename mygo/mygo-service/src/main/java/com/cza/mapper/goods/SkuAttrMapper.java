
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
import com.cza.dto.goods.TSkuAttr;

/**
    * @ClassName: UserMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    *
    */

public interface SkuAttrMapper {
	
	public List<TSkuAttr> listSkuAttrs(TSkuAttr param);
	
	public void saveSkuAttr(TSkuAttr skuAttr);
	
	public void updateSkuAttr(TSkuAttr skuAttr);

	
	    /**
	    * @Title: deleteAttrBySkuId
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param sid    参数
	    * @return void    返回类型
	    * @throws
	    */
	    
	public void deleteAttrBySkuId(Long sid);
}
