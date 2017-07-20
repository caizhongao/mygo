
    /**  
    * @Title: CategoryServiceImpl.java
    * @Package com.cza.service.goods.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年5月4日下午3:43:57
    * @version V1.0  
    */
    
package com.cza.service.goods.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.goods.TCategory;
import com.cza.dto.goods.TCategoryAttr;
import com.cza.mapper.goods.CategoryAttrMapper;
import com.cza.mapper.goods.CategoryMapper;
import com.cza.service.goods.CategoryService;
import com.cza.service.goods.vo.CategoryAttrVo;
import com.cza.service.goods.vo.CategoryVo;

/**
    * @ClassName: CategoryServiceImpl
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月4日下午3:43:57
    *
    */

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class); 
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private CategoryAttrMapper attrMapper;
	
	
	
	
    /* (非 Javadoc)
    * 
    * 
    * @param valueOf
    * @see com.cza.service.goods.GoodsService#listAttrs(java.lang.Long)
    */
    
@Override
public ServiceResponse<List<TCategoryAttr>> listAttrs(Long cid) {
	ServiceResponse<List<TCategoryAttr>> resp=new ServiceResponse<List<TCategoryAttr>>();
	try{	
		TCategoryAttr attrParam=new TCategoryAttr();
		attrParam.setCid(cid);
		attrParam.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
		List<TCategoryAttr> attrs=attrMapper.listAttrs(attrParam);
		resp.setData(attrs);
		resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
		resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
	} catch (Exception e) {
		resp.setData(null);
		resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
		resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
		log.error("查询attr失败",e);
	}
	return resp;
}
	
	/* (非 Javadoc)
	* 
	* 
	* @param category
	* @return
	* @see com.cza.service.goods.CategoryService#listCategory(com.cza.dto.goods.TCategory)
	*/

	@Override
	public ServiceResponse<List<CategoryVo>> listCategory(CategoryVo categoryVo) {
		ServiceResponse<List<CategoryVo>> resp=new ServiceResponse<>();
		try {
			TCategory listParam=new TCategory();
			listParam.setCid(categoryVo.getCid());
			listParam.setCname(categoryVo.getCname());
			listParam.setStatus(categoryVo.getStatus());
			listParam.setPid(categoryVo.getPid());
			List<TCategory> categoryList=categoryMapper.listCategory(listParam);
			if(categoryList!=null&&categoryList.size()>0){
				List<CategoryVo> voList=new ArrayList<>();
				for(TCategory category: categoryList){
					CategoryVo vo=new CategoryVo();
					vo.setCid(category.getCid());
					vo.setCname(category.getCname());
					vo.setStatus(category.getStatus());
					vo.setPid(category.getPid());
					vo.setOrderId(category.getOrderId());
					voList.add(vo);
				}
				resp.setData(voList);
			}
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			log.error("CategoryServiceImpl listCategory erro:",e);
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
		}
		return resp;
	}

	/* (非 Javadoc)
	* 
	* 
	* @param category
	* @return
	* @see com.cza.service.goods.CategoryService#updateCategory(com.cza.dto.goods.TCategory)
	*/

	@Override
	public ServiceResponse<CategoryVo> updateCategory(CategoryVo categoryVo) {
		ServiceResponse<CategoryVo> resp=new ServiceResponse<>();
		try {
			TCategory updateParam=new TCategory();
			updateParam.setCid(categoryVo.getCid());
			updateParam.setCname(categoryVo.getCname());
			updateParam.setStatus(categoryVo.getStatus());
			updateParam.setPid(categoryVo.getPid());
			updateParam.setOrderId(categoryVo.getOrderId());
			categoryMapper.updateCategory(updateParam);
			//更新
			TCategoryAttr  updateStatusParam=new TCategoryAttr();
			updateStatusParam.setCid(categoryVo.getCid());
			updateStatusParam.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_DELETE);
			attrMapper.updateAttrStatus(updateStatusParam);
			List<CategoryAttrVo> attrVoList=categoryVo.getAttrList();
			if(attrVoList!=null&&attrVoList.size()>0){
				for(CategoryAttrVo attrVo:attrVoList){
					if(attrVo.getCaid()==null){
						TCategoryAttr saveAttr=new TCategoryAttr();
						saveAttr.setCid(categoryVo.getCid());
						saveAttr.setAttrName(attrVo.getAttrName());
						saveAttr.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
						attrMapper.saveCategoryAttr(saveAttr);
					}else{
						TCategoryAttr updateAttr=new TCategoryAttr();
						updateAttr.setCaid(attrVo.getCaid());
						updateAttr.setCid(categoryVo.getCid());
						updateAttr.setAttrName(attrVo.getAttrName());
						updateAttr.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
						attrMapper.updateCategoryAttr(updateAttr);
					}
				}
			}
			resp.setData(categoryVo);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			log.error("CategoryServiceImpl updateCategory erro:",e);
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
		}
		return resp;
	}

	/* (非 Javadoc)
	* 
	* 
	* @param category
	* @return
	* @see com.cza.service.goods.CategoryService#saveCategory(com.cza.dto.goods.TCategory)
	*/

	@Override
	public ServiceResponse<CategoryVo> saveCategory(CategoryVo categoryVo) {
		ServiceResponse<CategoryVo> resp=new ServiceResponse<>();
		try {
			TCategory saveParam=new TCategory();
			saveParam.setCname(categoryVo.getCname());
			saveParam.setStatus(categoryVo.getStatus());
			saveParam.setOrderId(categoryVo.getOrderId());
			saveParam.setPid(0l);
			categoryMapper.saveCategory(saveParam);
			//保存类目属性
			List<CategoryAttrVo> attrVoList=categoryVo.getAttrList();
			if(attrVoList!=null&&attrVoList.size()>0){
				for(CategoryAttrVo attrVo:attrVoList){
					TCategoryAttr saveAttr=new TCategoryAttr();
					saveAttr.setCid(saveParam.getCid());
					saveAttr.setAttrName(attrVo.getAttrName());
					saveAttr.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
					attrMapper.saveCategoryAttr(saveAttr);
				}
			}
			resp.setData(categoryVo);
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			log.error("CategoryServiceImpl saveCategory erro:",e);
			resp.setData(null);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
		}
		return resp;
	}

	/* (非 Javadoc)
	* 
	* 
	* @param category
	* @return
	* @see com.cza.service.goods.CategoryService#queryCategory(com.cza.dto.goods.TCategory)
	*/

	@Override
	public ServiceResponse<CategoryVo> queryCategory(Long cid) {
		ServiceResponse<CategoryVo> resp=new ServiceResponse<>();
		try {
			TCategory category=categoryMapper.queryCategory(cid);
			if(category!=null){
				CategoryVo vo=new CategoryVo();
				vo.setCid(category.getCid());
				vo.setCname(category.getCname());
				vo.setPid(category.getPid());
				vo.setStatus(category.getStatus());
				vo.setOrderId(category.getOrderId());
				TCategoryAttr attrParam=new TCategoryAttr();
				attrParam.setCid(category.getCid());
				attrParam.setStatus(ShoppingContants.CATEGORY_ATTR_STATUS_NORMAL);
				List<CategoryAttrVo> attrVoList=null;
				List<TCategoryAttr>attrList=attrMapper.listAttrs(attrParam);
				if(attrList!=null&&attrList.size()>0){
					attrVoList=new ArrayList<>();
					for(TCategoryAttr attr:attrList){
						CategoryAttrVo attrVo=new CategoryAttrVo();
						attrVo.setAttrName(attr.getAttrName());
						attrVo.setCaid(attr.getCaid());
						attrVoList.add(attrVo);
					}
				}
				vo.setAttrList(attrVoList);
				resp.setData(vo);
			}
			resp.setMsg(ShoppingContants.RESP_MSG_SUCESS);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
		} catch (Exception e) {
			log.error("CategoryServiceImpl listCategory erro:",e);
			resp.setMsg(ShoppingContants.RESP_MSG_SYSTEM_ERRO);
			resp.setCode(ShoppingContants.RESP_CODE_SYSTEM_ERRO);
		}
		return resp;
	}

}
