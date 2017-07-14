
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
	private Long oid;         
	private Long uid ;   
	private String userName;
	private String province;              
	private String city   ;              
	private String area  ;              
	private String addr  ;     
	private String receiver;        
	private String mobilphone;  
	private Long sid;           
	private BigDecimal orderPrice;     
	private BigDecimal amount;
	private Long number;
	private Integer payStatus;
	private Integer status;
	private String deleteDesc;
	
	private String payNo;
	
	
	private Long createTime;
	
	private Long updateTime;
	private Long orderVersion;
	
	private Long gid;
	private String goodsName;
	
	
	
	
	/**
	* @return userName
	*/
	
	public String getUserName() {
		return userName;
	}


	
	/**
	 * @param userName the userName to set
	 */
	
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	* @return gid
	*/
	
	public Long getGid() {
		return gid;
	}

	
	/**
	 * @param gid the gid to set
	 */
	
	public void setGid(Long gid) {
		this.gid = gid;
	}

	
	/**
	* @return goodsName
	*/
	
	public String getGoodsName() {
		return goodsName;
	}

	
	/**
	 * @param goodsName the goodsName to set
	 */
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	* @return oid
	*/
	
	public Long getOid() {
		return oid;
	}
	
	/**
	 * @param oid the oid to set
	 */
	
	public void setOid(Long oid) {
		this.oid = oid;
	}
	
	/**
	* @return uid
	*/
	
	public Long getUid() {
		return uid;
	}
	
	/**
	 * @param uid the uid to set
	 */
	
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	/**
	* @return province
	*/
	
	public String getProvince() {
		return province;
	}
	
	/**
	 * @param province the province to set
	 */
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**
	* @return city
	*/
	
	public String getCity() {
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	* @return area
	*/
	
	public String getArea() {
		return area;
	}
	
	/**
	 * @param area the area to set
	 */
	
	public void setArea(String area) {
		this.area = area;
	}
	
	/**
	* @return addr
	*/
	
	public String getAddr() {
		return addr;
	}
	
	/**
	 * @param addr the addr to set
	 */
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	/**
	* @return receiver
	*/
	
	public String getReceiver() {
		return receiver;
	}
	
	/**
	 * @param receiver the receiver to set
	 */
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	/**
	* @return mobilphone
	*/
	
	public String getMobilphone() {
		return mobilphone;
	}
	
	/**
	 * @param mobilphone the mobilphone to set
	 */
	
	public void setMobilphone(String mobilphone) {
		this.mobilphone = mobilphone;
	}
	
	/**
	* @return sid
	*/
	
	public Long getSid() {
		return sid;
	}
	
	/**
	 * @param sid the sid to set
	 */
	
	public void setSid(Long sid) {
		this.sid = sid;
	}
	
	/**
	* @return orderPrice
	*/
	
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	
	/**
	 * @param orderPrice the orderPrice to set
	 */
	
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	/**
	* @return amount
	*/
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * @param amount the amount to set
	 */
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	* @return number
	*/
	
	public Long getNumber() {
		return number;
	}
	
	/**
	 * @param number the number to set
	 */
	
	public void setNumber(Long number) {
		this.number = number;
	}
	
	/**
	* @return payStatus
	*/
	
	public Integer getPayStatus() {
		return payStatus;
	}
	
	/**
	 * @param payStatus the payStatus to set
	 */
	
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	
	/**
	* @return status
	*/
	
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	* @return deleteDesc
	*/
	
	public String getDeleteDesc() {
		return deleteDesc;
	}
	
	/**
	 * @param deleteDesc the deleteDesc to set
	 */
	
	public void setDeleteDesc(String deleteDesc) {
		this.deleteDesc = deleteDesc;
	}
	
	/**
	* @return payNo
	*/
	
	public String getPayNo() {
		return payNo;
	}
	
	/**
	 * @param payNo the payNo to set
	 */
	
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	
	/**
	* @return createTime
	*/
	
	public Long getCreateTime() {
		return createTime;
	}
	
	/**
	 * @param createTime the createTime to set
	 */
	
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	/**
	* @return updateTime
	*/
	
	public Long getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * @param updateTime the updateTime to set
	 */
	
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	* @return orderVersion
	*/
	
	public Long getOrderVersion() {
		return orderVersion;
	}
	
	/**
	 * @param orderVersion the orderVersion to set
	 */
	
	public void setOrderVersion(Long orderVersion) {
		this.orderVersion = orderVersion;
	}
	
	/**
	* @return serialversionuid
	*/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "TOrder [oid=" + oid + ", uid=" + uid + ", province=" + province + ", city=" + city + ", area=" + area
				+ ", addr=" + addr + ", receiver=" + receiver + ", mobilphone=" + mobilphone + ", sid=" + sid
				+ ", orderPrice=" + orderPrice + ", amount=" + amount + ", number=" + number + ", payStatus="
				+ payStatus + ", status=" + status + ", deleteDesc=" + deleteDesc + ", payNo=" + payNo + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", orderVersion=" + orderVersion + ", gid=" + gid
				+ ", goodsName=" + goodsName + "]";
	}

	
	    

	

	
}
