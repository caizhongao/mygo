
    /**  
    * @Title: AddrServiceImpl.java
    * @Package com.cza.service.user.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月8日下午5:34:59
    * @version V1.0  
    */
    
package com.cza.service.user.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.addr.TArea;
import com.cza.dto.addr.TUserAddr;
import com.cza.mapper.addr.AreaMapper;
import com.cza.mapper.addr.UserAddrMapper;
import com.cza.service.user.AddrService;

/**
    * @ClassName: AddrServiceImpl
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月8日下午5:34:59
    *
    */
@Service("addrService")
public class AddrServiceImpl implements AddrService {
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private UserAddrMapper addrMapper;
	private static final Logger log = LoggerFactory.getLogger(AddrServiceImpl.class); 
	/* (非 Javadoc)
	* 
	* 
	* @param area
	* @return
	* @see com.cza.service.user.AddrService#listAreas(com.cza.dto.addr.TArea)
	*/

	@Override
	public ServiceResponse<List<TArea>> listAreas(TArea area) {
		ServiceResponse<List<TArea>> resp=new ServiceResponse<List<TArea>>();
		try{
			List<TArea> areas=areaMapper.listAreas(area);
			resp.setData(areas);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUC);
			log.info("listAreas success,param:{},result:{}",area,areas);
		} catch (Exception e) {
			log.error("查询TArea失敗",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_FAIL);
		}
		return resp;
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param addr
	    * @return
	    * @see com.cza.service.user.AddrService#listAddrs(com.cza.dto.addr.TUserAddr)
	    */
	    
	@Override
	public ServiceResponse<List<TUserAddr>> listAddrs(TUserAddr addr) {
		ServiceResponse<List<TUserAddr>> resp=new ServiceResponse<List<TUserAddr>>();
		try{
			List<TUserAddr> addrs=addrMapper.listAddrs(addr);
			resp.setData(addrs);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUC);
			log.info("listAddrs success,param:{},result:{}",addr,addrs);
		} catch (Exception e) {
			log.error("查询TUserAddr失敗",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_FAIL);
		}
		return resp;
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param addr
	    * @return
	    * @see com.cza.service.user.AddrService#setDefault(com.cza.dto.addr.TUserAddr)
	    */
	    
	@Override
	public ServiceResponse<TUserAddr> setDefault(TUserAddr addr) {
		ServiceResponse<TUserAddr> resp=new ServiceResponse<TUserAddr>();
		try{
			TUserAddr param=new TUserAddr();
			param.setUid(addr.getUid());
			param.setIsDefault(ShoppingContants.ADDR_NOT_DEFAULT);
			addrMapper.updateNotDefault(addr);
			TUserAddr param1=new TUserAddr();
			param1.setIsDefault(ShoppingContants.ADDR_IS_DEFAULT);
			param1.setUaid(addr.getUaid());
			addrMapper.updateAddr(param1);
			resp.setData(addr);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUC);
			log.info("setDefault success,param:{}",addr);
		} catch (Exception e) {
			log.error("TUserAddr设置为默认失败",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_FAIL);
		}
		return resp;
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param addr
	    * @return
	    * @see com.cza.service.user.AddrService#saveAddr(com.cza.dto.addr.TUserAddr)
	    */
	    
	@Override
	public ServiceResponse<TUserAddr> saveAddr(TUserAddr addr) {
		ServiceResponse<TUserAddr> resp=new ServiceResponse<TUserAddr>();
		try{
			if(ShoppingContants.ADDR_IS_DEFAULT.equals(addr.getIsDefault())){
				TUserAddr updateParam=new TUserAddr();
				updateParam.setUid(addr.getUid());
				updateParam.setIsDefault(ShoppingContants.ADDR_NOT_DEFAULT);
				addrMapper.updateNotDefault(updateParam);
			}
			if(addr.getUaid()!=null){
				addrMapper.updateAddr(addr);
			}else{
				addrMapper.saveAddr(addr);
			}
			resp.setData(addr);
			resp.setCode(ShoppingContants.RESP_CODE_SUCESS);
			resp.setMsg(ShoppingContants.RESP_MSG_SUC);
			log.info("saveAddr success,param:{}",addr);
		} catch (Exception e) {
			log.error("保存TUserAddr失败",e);
			resp.setData(null);
			resp.setCode(ShoppingContants.RESP_CODE_ERRO);
			resp.setMsg(ShoppingContants.RESP_MSG_FAIL);
		}
		return resp;
	}

}
