
    /**  
    * @Title: UserVo.java
    * @Package com.cza.web.user.vo
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月17日下午4:50:13
    * @version V1.0  
    */
    
package com.cza.service.user.vo;

import java.io.Serializable;

/**
    * @ClassName: UserVo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月17日下午4:50:13
    *
    */

public class UserVo implements Serializable{
	 
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = -408038276779482497L;
	private String  userName;
	 private String  password;
	 private String  sex;
	 private String   realName;
	 private Integer  age;
	 private Long  uid;
	 private String picCode;
	 private String ref;
	 private String type;
	 
	 private Integer rememberMe;
	 
	 
	
	
	
	
	
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
	* @return rememberMe
	*/
	
	public Integer getRememberMe() {
		return rememberMe;
	}



	
	/**
	 * @param rememberMe the rememberMe to set
	 */
	
	public void setRememberMe(Integer rememberMe) {
		this.rememberMe = rememberMe;
	}



	/**
	* @return ref
	*/
	
	public String getRef() {
		return ref;
	}


	
	/**
	 * @param ref the ref to set
	 */
	
	public void setRef(String ref) {
		this.ref = ref;
	}


	/**
	* @return picCode
	*/
	
	public String getPicCode() {
		return picCode;
	}

	
	/**
	 * @param picCode the picCode to set
	 */
	
	public void setPicCode(String picCode) {
		this.picCode = picCode;
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





	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @return
	    * @see java.lang.Object#toString()
	    */
	    
	@Override
	public String toString() {
		return "UserVo [userName=" + userName + ", password=" + password + ", sex=" + sex + ", realName=" + realName
				+ ", age=" + age + ", uid=" + uid + ", picCode=" + picCode + ", ref=" + ref + ", type=" + type
				+ ", rememberMe=" + rememberMe + "]";
	}
	
	
	/* (非 Javadoc)
	* 
	* 
	* @return
	* @see java.lang.Object#toString()
	*/
	
	
	
	 
}
