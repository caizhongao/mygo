
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
	public void listAreas(HttpServletRequest request,HttpServletResponse response) throws IOException{
		TArea area=new TArea();
		area.setPaid(Long.valueOf(request.getParameter("paid")));
		log.info("AddrAction.listAreas 请求参数,paid:{}",area.getPaid());
		ServiceResponse<List<TArea>>resp=addrService.listAreas(area);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			response.setCharacterEncoding("UTF-8");
			List<TArea> areas=resp.getData();
			String result=JSON.toJSONString(areas);
			log.info("AddrAction.listAreas 响应结果:{}",result);
			response.getWriter().println(result);
		}else{
			log.info("AddrAction.listAreas 查询失败");
		}
	}
	@RequestMapping("editAddr")
	public String editAddr(HttpServletRequest request,HttpServletResponse response){
		UserVo userVo=getUser(request);
		TUserAddr listParam=new TUserAddr();
		listParam.setUid(userVo.getUid());
		ServiceResponse<List<TUserAddr>>resp=addrService.listAddrs(listParam);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp.getCode())){
			List<TUserAddr> addrs=resp.getData();
			request.setAttribute("addrs", addrs);
			if(addrs!=null){
				//如果uaid不为空，代表编辑
				String uaid=request.getParameter("uaid");
				if(!StringUtils.isEmpty(uaid)){
					for(TUserAddr addr:addrs){
						if(addr.getUaid().equals(Long.valueOf(uaid))){
							request.setAttribute("addr", addr);
							break;
						}
					}
				}
			}
		}
		TArea area=new TArea();
		area.setPaid(0l);
		ServiceResponse<List<TArea>>resp1=addrService.listAreas(area);
		if(ShoppingContants.RESP_CODE_SUCESS.equals(resp1.getCode())){
			List<TArea> provinces=resp1.getData();
			request.setAttribute("provinces", provinces);
		}
		return webPage("/user/editAddress");
	}
	@RequestMapping("saveAddr")
	public String saveAddr(@ModelAttribute TUserAddr addr,HttpServletRequest request,HttpServletResponse response ){
		UserVo userVo=getUser(request);
		addr.setUid(userVo.getUid());
		if(addr.getIsDefault()==null){
			addr.setIsDefault(0);
		}
		ServiceResponse<TUserAddr>resp=addrService.saveAddr(addr);
		if(resp.isSuccess()){
			return webAction("/login/addr/editAddr.do");
		}else{
			return erroPage(resp.getCode());
		}
	}
	
	
	@RequestMapping("saveAddrAjax")
	public void saveAddrAjax(@ModelAttribute TUserAddr addr,HttpServletRequest request,HttpServletResponse response ) throws IOException{
		UserVo userVo=getUser(request);
		addr.setUid(userVo.getUid());
		if(addr.getIsDefault()==null){
			addr.setIsDefault(0);
		}
		ServiceResponse<TUserAddr>resp=addrService.saveAddr(addr);
		if(resp.isSuccess()){
			response.getWriter().print("success");
		}else{
			response.getWriter().print(resp.getCode());
		}
	}
	
	@RequestMapping("setDefault")
	public String setDefault(HttpServletRequest request,HttpServletResponse response){
		UserVo userVo=getUser(request);
		TUserAddr addr=new TUserAddr();
		addr.setUid(userVo.getUid());
		addr.setUaid(Long.valueOf(request.getParameter("uaid")));
		ServiceResponse<TUserAddr>resp=addrService.setDefault(addr);
		return webAction("/login/addr/editAddr.do");
	}
		
	
	
}
