
    /**  
    * @Title: TOrder.java
    * @Package com.cza.dto.order
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年5月3日下午5:50:50
    * @version V1.0  
    */
    
package com.cza.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
    * @ClassName: TOrder
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年5月3日下午5:50:50
    *
    */

public class TOrder implements Serializable{
	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = -6854927376341586525L;
	private String oid;         
	private Long uid ;   
	private String userName;
	private String province;              
	private String city   ;              
	private String area  ;              
	private String addr  ;     
	private String receiver;        
	private String mobilphone;  
	private BigDecimal amount;
	private Integer status;
	private String deleteDesc;
	private String payNo;
	private Long createTime;
	private Long updateTime;
	private Long orderVersion;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMobilphone() {
		return mobilphone;
	}
	public void setMobilphone(String mobilphone) {
		this.mobilphone = mobilphone;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDeleteDesc() {
		return deleteDesc;
	}
	public void setDeleteDesc(String deleteDesc) {
		this.deleteDesc = deleteDesc;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public Long getOrderVersion() {
		return orderVersion;
	}
	public void setOrderVersion(Long orderVersion) {
		this.orderVersion = orderVersion;
	}
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "TOrder [oid=" + oid + ", uid=" + uid + ", userName=" + userName + ", province=" + province + ", city="
				+ city + ", area=" + area + ", addr=" + addr + ", receiver=" + receiver + ", mobilphone=" + mobilphone
				+ ", amount=" + amount + ", status=" + status + ", deleteDesc=" + deleteDesc + ", payNo=" + payNo
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", orderVersion=" + orderVersion + "]";
	}
	
	
	
}
