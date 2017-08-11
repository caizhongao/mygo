
    /**  
    * @Title: ParamService.java
    * @Package com.cza.service.system
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年8月9日下午5:06:13
    * @version V1.0  
    */
    
package com.cza.service.system;

import java.util.List;

import com.cza.common.ServiceResponse;
import com.cza.dto.system.TParam;

/**
    * @ClassName: ParamService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年8月9日下午5:06:13
    *
    */

public interface ParamService {
	public ServiceResponse<TParam> saveOrUpdateParam(TParam param);
	
	public ServiceResponse<TParam> updateParam(TParam param);
	
	public ServiceResponse<TParam> queryParam(TParam param);
	
	public ServiceResponse<List<TParam>> listParam(TParam param);

	
	    /**
	    * @Title: deleteParam
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param param
	    * @param @return    参数
	    * @return ServiceResponse<TParam>    返回类型
	    * @throws
	    */
	    
	public ServiceResponse<TParam> deleteParam(TParam param);
}
