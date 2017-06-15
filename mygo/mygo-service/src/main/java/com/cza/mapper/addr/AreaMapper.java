
    /**  
    * @Title: AreaMapper.java
    * @Package com.cza.mapper.addr
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月8日下午5:26:55
    * @version V1.0  
    */
    
package com.cza.mapper.addr;

import java.util.List;

import com.cza.dto.addr.TArea;

/**
    * @ClassName: AreaMapper
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月8日下午5:26:55
    *
    */

public interface AreaMapper {
	public List<TArea> listAreas(TArea area);
}
