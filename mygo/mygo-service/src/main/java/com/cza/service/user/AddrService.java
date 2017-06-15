
    /**  
    * @Title: AddrService.java
    * @Package com.cza.service.user
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月8日下午5:31:48
    * @version V1.0  
    */
    
package com.cza.service.user;

import java.util.List;


import com.cza.common.ServiceResponse;
import com.cza.dto.addr.TArea;
import com.cza.dto.addr.TUserAddr;

/**
    * @ClassName: AddrService
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月8日下午5:31:48
    *
    */

public interface AddrService {

	
	
	public ServiceResponse<List<TArea>> listAreas(TArea area);

	
	    /**
	    * @Title: listAddrs
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param addr
	    * @param @return    参数
	    * @return ServiceResponse<List<TUserAddr>>    返回类型
	    * @throws
	    */
	    
	public ServiceResponse<List<TUserAddr>> listAddrs(TUserAddr addr);


		
		    /**
		     * @return 
		    * @Title: setDefault
		    * @Description: TODO(这里用一句话描述这个方法的作用)
		    * @param @param addr    参数
		    * @return void    返回类型
		    * @throws
		    */
		    
		public ServiceResponse<TUserAddr> setDefault(TUserAddr addr);


			
			    /**
			     * @return 
			    * @Title: saveAddr
			    * @Description: TODO(这里用一句话描述这个方法的作用)
			    * @param @param addr    参数
			    * @return void    返回类型
			    * @throws
			    */
			    
			public ServiceResponse<TUserAddr> saveAddr(TUserAddr addr);
}
