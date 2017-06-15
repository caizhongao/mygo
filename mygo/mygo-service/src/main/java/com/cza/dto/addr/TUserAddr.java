
    /**  
    * @Title: TUserAddr.java
    * @Package com.cza.dto.addr
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年3月8日下午5:44:42
    * @version V1.0  
    */
    
package com.cza.dto.addr;


    /**
    * @ClassName: TUserAddr
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年3月8日下午5:44:42
    *
    */

public class TUserAddr {
	private Long uaid;
	private String province;                                
	private String city;                                
	private String area;                                
	private String addr ;                          
	private Long provinceId;                              
	private Long cityId    ;                              
	private Long areaId     ;                              
	private Long uid          ;                              
	private Integer isDefault;  
	private String receiver   ;                             
	private String mobilphone  ;
	
	/**
	* @return uaid
	*/
	
	public Long getUaid() {
		return uaid;
	}
	
	/**
	 * @param uaid the uaid to set
	 */
	
	public void setUaid(Long uaid) {
		this.uaid = uaid;
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
	* @return provinceId
	*/
	
	public Long getProvinceId() {
		return provinceId;
	}
	
	/**
	 * @param provinceId the provinceId to set
	 */
	
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	
	/**
	* @return cityId
	*/
	
	public Long getCityId() {
		return cityId;
	}
	
	/**
	 * @param cityId the cityId to set
	 */
	
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	/**
	* @return areaId
	*/
	
	public Long getAreaId() {
		return areaId;
	}
	
	/**
	 * @param areaId the areaId to set
	 */
	
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
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
	* @return isDefault
	*/
	
	public Integer getIsDefault() {
		return isDefault;
	}
	
	/**
	 * @param isDefault the isDefault to set
	 */
	
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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

	@Override
	public String toString() {
		return "TUserAddr [uaid=" + uaid + ", province=" + province + ", city=" + city + ", area=" + area + ", addr=" + addr + ", provinceId=" + provinceId + ", cityId=" + cityId + ", areaId=" + areaId + ", uid=" + uid + ", isDefault=" + isDefault + ", receiver=" + receiver + ", mobilphone=" + mobilphone + "]";
	}    

	
}
