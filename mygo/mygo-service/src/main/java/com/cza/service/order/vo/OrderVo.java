
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
import java.util.List;

import com.cza.dto.order.TOrder;
import com.cza.dto.order.TOrderDetail;

/**
    * @ClassName: ListOrderVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年6月15日上午11:03:12
    *
    */

public class OrderVo extends TOrder{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5615552396138482416L;
	
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
	
	private List<OrderDetailVo> detailVos;
	private String token;
	private Long addrId;
	//默认是立即购买，1代表购物袋
	private Integer type=0;
	
	//查询参数
	private String goodsName;
	
	private String orderName;
	private Integer pageNum=1;
	private Integer pageSize=15;
	private Integer start=0;


	public Integer getType() {
		return type;
	}





	public void setType(Integer type) {
		this.type = type;
	}





	public String getGoodsName() {
		return goodsName;
	}





	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}





	public Long getAddrId() {
		return addrId;
	}





	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}





	public String getToken() {
		return token;
	}





	public void setToken(String token) {
		this.token = token;
	}





	public List<OrderDetailVo> getDetailVos() {
		return detailVos;
	}





	public void setDetailVos(List<OrderDetailVo> detailVos) {
		this.detailVos = detailVos;
	}





	public String getOrderName() {
		return orderName;
	}





	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}





	public Integer getPageNum() {
		return pageNum;
	}





	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}





	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}





	public Integer getStart() {
		return start;
	}



	public void setStart(Integer start) {
		this.start = start;
	}


	
	/**
	* @return oid
	*/
	
	public String getOid() {
		return oid;
	}





	
	/**
	 * @param oid the oid to set
	 */
	
	public void setOid(String oid) {
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





	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "OrderVo [oid=" + oid + ", uid=" + uid + ", userName=" + userName + ", province=" + province + ", city="
				+ city + ", area=" + area + ", addr=" + addr + ", receiver=" + receiver + ", mobilphone=" + mobilphone
				+ ", amount=" + amount + ", status=" + status + ", deleteDesc=" + deleteDesc + ", payNo=" + payNo
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", orderVersion=" + orderVersion
				+ ", detailVos=" + detailVos + ", token=" + token + ", addrId=" + addrId + ", type=" + type
				+ ", goodsName=" + goodsName + ", orderName=" + orderName + ", pageNum=" + pageNum + ", pageSize="
				+ pageSize + ", start=" + start + "]";
	}


}
