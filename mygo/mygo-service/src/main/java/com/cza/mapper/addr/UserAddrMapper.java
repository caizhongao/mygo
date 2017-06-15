
    /**  
    * @Title: UserAddrMapper.java
    * @Package com.cza.mapper.addr
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月8日下午5:47:00
    * @version V1.0  
    */
    
package com.cza.mapper.addr;

import java.util.List;

import com.cza.dto.addr.TUserAddr;

/**
    * @ClassName: UserAddrMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月8日下午5:47:00
    *
    */

public interface UserAddrMapper {
	void saveAddr(TUserAddr addr);
	
	void updateAddr(TUserAddr addr);

	List<TUserAddr> listAddrs(TUserAddr addr);

	TUserAddr queryAddr(Long uaid);
	    /**
	    * @Title: updateNotDefault
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param addr    参数
	    * @return void    返回类型
	    * @throws
	    */
	    
	void updateNotDefault(TUserAddr addr);
}
