package com.cza.mapper.goods;
    /**  
    * @Title: CategoryMapper.java
    * @Package com.cza.mapper.Category
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年5月4日下午2:50:19
    * @version V1.0  
    */
    

import java.util.List;

import com.cza.dto.goods.TCategory;


/**
    * @ClassName: CategoryMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月4日下午2:50:19
    *
    */

public interface CategoryMapper {
	
	public List<TCategory> listCategory(TCategory param);
	
	public TCategory queryCategory(Long cid);
	
	public void saveCategory(TCategory Category);
	
	public void updateCategory(TCategory Category);
}
