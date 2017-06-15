
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

import com.cza.dto.goods.TCategoryAttr;

/**
    * @ClassName: UserMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    *
    */

public interface CategoryAttrMapper {
	
	public List<TCategoryAttr> listAttrs(TCategoryAttr param);
	
	public TCategoryAttr queryAttr(Long caid);

	public void saveCategoryAttr(TCategoryAttr attr);
	
	public void updateCategoryAttr(TCategoryAttr attr);
	
	public void updateAttrStatus(TCategoryAttr attr);
}
