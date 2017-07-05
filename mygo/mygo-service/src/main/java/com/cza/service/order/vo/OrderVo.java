
    /**  
    * @Title: ListOrderVo.java
    * @Package com.cza.service.order.vo
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年6月15日上午11:03:12
    * @version V1.0  
    */
    
package com.cza.service.order.vo;

import java.math.BigDecimal;

/**
    * @ClassName: ListOrderVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年6月15日上午11:03:12
    *
    */

public class OrderVo {
	private Long oid;         
	private Long uid ;         
	private String province;              
	private String city   ;              
	private String area  ;              
	private String addr  ;     
	private String receiver;        
	private String mobilphone;  
	private Long sid;           
	private String goodsName;     
	private BigDecimal orderPrice;     
	private BigDecimal amount;
	private Long number;
	private Integer payStatus;
	private Integer status;
	private String deleteDesc;
	
	private String userName;
	
	private String payNo;
	private Long createTime;
	private Long updateTime;
	
	private Long orderVersion;
	
	
	private Integer pageNum=1;
	private Integer pageSize=15;
	private Integer start=0;
	
	
	
	
	
	
	
	
	
	
	
	
	
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
	* @return start
	*/
	
	public Integer getStart() {
		return start;
	}






	
	/**
	 * @param start the start to set
	 */
	
	public void setStart(Integer start) {
		this.start = start;
	}






	/**
	* @return pageNum
	*/
	
	public Integer getPageNum() {
		return pageNum;
	}





	
	/**
	 * @param pageNum the pageNum to set
	 */
	
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}





	
	/**
	* @return pageSize
	*/
	
	public Integer getPageSize() {
		return pageSize;
	}





	
	/**
	 * @param pageSize the pageSize to set
	 */
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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









	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "OrderVo [oid=" + oid + ", uid=" + uid + ", province=" + province + ", city=" + city + ", area=" + area
				+ ", addr=" + addr + ", receiver=" + receiver + ", mobilphone=" + mobilphone + ", sid=" + sid
				+ ", goodsName=" + goodsName + ", orderPrice=" + orderPrice + ", amount=" + amount + ", number="
				+ number + ", payStatus=" + payStatus + ", status=" + status + ", deleteDesc=" + deleteDesc
				+ ", userName=" + userName + ", payNo=" + payNo + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", orderVersion=" + orderVersion + ", pageNum=" + pageNum + ", pageSize=" + pageSize
				+ ", start=" + start + "]";
	}


	
	
}
