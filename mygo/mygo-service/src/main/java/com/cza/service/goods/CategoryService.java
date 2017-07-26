
    /**  
    * @Title: CategoryService.java
    * @Package com.cza.service.goods
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年5月4日下午3:40:45
    * @version V1.0  
    */
    
package com.cza.service.goods;

import java.util.List;

import com.cza.common.ServiceResponse;
import com.cza.dto.goods.TCategory;
import com.cza.dto.goods.TCategoryAttr;
import com.cza.service.goods.vo.CategoryVo;

/**
    * @ClassName: CategoryService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月4日下午3:40:45
    *
    */

public interface CategoryService {
	ServiceResponse<List<CategoryVo>> listCategory(CategoryVo category);
	
	ServiceResponse<CategoryVo> updateCategory(CategoryVo category);
	
	ServiceResponse<CategoryVo> saveCategory(CategoryVo category);
	
    
	ServiceResponse<List<TCategoryAttr>> listAttrs(Long cid);
	
	ServiceResponse<CategoryVo> queryCategory(Long cid);
}
