
    /**  
    * @Title: HomeAction.java
    * @Package com.cza.web.home.action
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月21日下午4:45:18
    * @version V1.0  
    */
    
package com.cza.web.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cza.common.RespMsg;
import com.cza.common.ServiceResponse;
import com.cza.common.ShoppingContants;
import com.cza.dto.addr.TArea;
import com.cza.dto.addr.TUserAddr;
import com.cza.service.user.AddrService;
import com.cza.service.user.vo.UserVo;
import com.cza.web.CommonAction;

/**
    * @ClassName: HomeAction
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月21日下午4:45:18
    *
    */
@Controller
@Scope("prototype")
@RequestMapping("/login/addr")
public class AddrAction extends CommonAction{
	@Autowired
	private AddrService addrService;
	private static final Logger log = LoggerFactory.getLogger(AddrAction.class); 
	@RequestMapping("listAreas")
	public void listAreas(@ModelAttribute TArea area, HttpServletRequest request,HttpServletResponse response) throws IOException{
		log.info("AddrAction.listAreas request params:{}",area);
		response.setCharacterEncoding("UTF-8");
		ServiceResponse<List<TArea>>resp=addrService.listAreas(area);
		if(resp.isSuccess()){
			log.info("AddrAction.listAreas success,result:{}",resp.getData());
			response.getWriter().println(new RespMsg("success", resp.getData()));
		}else{
			log.info("AddrAction.listAreas has erro,respCode:{}",resp.getCode());
			response.getWriter().println(new RespMsg("fail", resp.getCode()));
		}
	}
	@RequestMapping("editAddr")
	public String editAddr(HttpServletRequest request,HttpServletResponse response){
		UserVo userVo=getUser(request);
		TUserAddr listParam=new TUserAddr();
		listParam.setUid(userVo.getUid());
		ServiceResponse<List<TUserAddr>>resp=addrService.listAddrs(listParam);
		if(resp.isSuccess()){
			log.info("listAddr success,result:{}",resp.getData());
			List<TUserAddr> addrs=resp.getData();
			request.setAttribute("addrs", addrs);
			String uaid=request.getParameter("uaid");
			log.info("editAddr uaid:{}",uaid);
			//如果uaid不为空，代表编辑
			if(!StringUtils.isEmpty(uaid)){
				if(addrs!=null&&addrs.size()>0){
					for(TUserAddr addr:addrs){
						if(addr.getUaid().equals(Long.valueOf(uaid))){
							request.setAttribute("addr", addr);
							break;
						}
					}
				}
			}
		}else{
			log.info("listAddr has erro,respCode:{}",resp.getCode());
			return erroPage(resp.getCode());
		}
		TArea area=new TArea();
		area.setPaid(0l);
		ServiceResponse<List<TArea>>areaResp=addrService.listAreas(area);
		if(areaResp.isSuccess()){
			log.info("AddrAction.listAreas success,result:{}",areaResp.getData());
			List<TArea> provinces=areaResp.getData();
			request.setAttribute("provinces", provinces);
		}else{
			log.info("listAreas has erro,respCode:{}",areaResp.getCode());
			return erroPage(areaResp.getCode());
		}
		return webPage("/user/editAddress");
	}
	@RequestMapping("saveAddr")
	public String saveAddr(@ModelAttribute TUserAddr addr,HttpServletRequest request,HttpServletResponse response ){
		log.info("saveAddr request param:{}",addr);
		UserVo userVo=getUser(request);
		addr.setUid(userVo.getUid());
		if(addr.getIsDefault()==null){
			addr.setIsDefault(0);
		}
		ServiceResponse<TUserAddr>resp=addrService.saveAddr(addr);
		if(resp.isSuccess()){
			log.info("saveAddr success!");
			return webAction("/login/addr/editAddr");
		}else{
			log.warn("saveAddr has erro,respCode:{}",resp.getCode());
			return erroPage(resp.getCode());
		}
	}
	
	
	@RequestMapping("saveAddrAjax")
	public void saveAddrAjax(@ModelAttribute TUserAddr addr,HttpServletRequest request,HttpServletResponse response ) throws IOException{
		log.info("saveAddrAjax request param:{}",addr);
		response.setCharacterEncoding("utf-8");
		UserVo userVo=getUser(request);
		addr.setUid(userVo.getUid());
		if(addr.getIsDefault()==null){
			addr.setIsDefault(0);
		}
		ServiceResponse<TUserAddr>resp=addrService.saveAddr(addr);
		if(resp.isSuccess()){
			log.info("saveAddrAjax success!");
			response.getWriter().println(new RespMsg("success", resp.getData()).toJson());
		}else{
			log.warn("saveAddrAjax has erro,respCode:{}",resp.getCode());
			response.getWriter().println(new RespMsg("fail", resp.getCode()).toJson());
		}
	}
	
	@RequestMapping("setDefault")
	public String setDefault(@ModelAttribute TUserAddr addr,HttpServletRequest request,HttpServletResponse response){
		log.info("setDefault request param:{}",addr);
		UserVo userVo=getUser(request);
		addr.setUid(userVo.getUid());
		ServiceResponse<TUserAddr>resp=addrService.setDefault(addr);
		if(resp.isSuccess()){
			log.info("setDefault success!");
			return webAction("/login/addr/editAddr");
		}else{
			log.warn("setDefault has erro,respCode:{}",resp.getCode());
			return erroPage(resp.getCode());
		}
	}
		
	
	
}
