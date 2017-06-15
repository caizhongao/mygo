
    /**  
    * @Title: TUser.java
    * @Package com.cza.dto.user
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:18:25
    * @version V1.0  
    */
    
package com.cza.dto.user;


    /**
    * @ClassName: TUser
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:18:25
    *
    */

public class TUser {
	 private String  userName;
	 private String  password;
	 private String  sex;
	 private String   realName;
	 private Integer  age;
	 private Long  createTime;
	 private Long  updateTime;
	 private Long  uid;
	 private String type;
	 
	 
	
	
	/**
	* @return type
	*/
	
	public String getType() {
		return type;
	}

	
	/**
	 * @param type the type to set
	 */
	
	public void setType(String type) {
		this.type = type;
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
	* @return password
	*/
	
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	* @return sex
	*/
	
	public String getSex() {
		return sex;
	}
	
	/**
	 * @param sex the sex to set
	 */
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	* @return realName
	*/
	
	public String getRealName() {
		return realName;
	}
	
	/**
	 * @param realName the realName to set
	 */
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	/**
	* @return age
	*/
	
	public Integer getAge() {
		return age;
	}
	
	/**
	 * @param age the age to set
	 */
	
	public void setAge(Integer age) {
		this.age = age;
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


	@Override
	public String toString() {
		return "TUser [userName=" + userName + ", password=" + password + ", sex=" + sex + ", realName=" + realName + ", age=" + age + ", createTime=" + createTime + ", updateTime=" + updateTime + ", uid=" + uid + ", type=" + type + "]";
	}
	 
	 
}
