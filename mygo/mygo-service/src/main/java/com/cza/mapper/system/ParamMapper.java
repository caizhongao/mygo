
    /**  
    * @Title: UserMapper.java
    * @Package com.cza.mapper.user
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    * @version V1.0  
    */
    
package com.cza.mapper.system;

import java.util.List;

import com.cza.dto.system.TParam;

/**
    * @ClassName: UserMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:22:28
    *
    */

public interface ParamMapper {
	public void saveParam(TParam param);
	
	public void updateParam(TParam param);
	
	public TParam queryParam(TParam param);
	
	public List<TParam> listParam(TParam param);

	
	    /**
	    * @Title: deleteParam
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param param    参数
	    * @return void    返回类型
	    * @throws
	    */
	    
	public void deleteParam(TParam param);
}
