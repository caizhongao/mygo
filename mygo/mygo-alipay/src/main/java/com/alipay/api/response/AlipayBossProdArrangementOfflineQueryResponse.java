package com.alipay.api.response;

import com.alipay.api.internal.mapping.ApiField;

import com.alipay.api.AlipayResponse;

/**
 * ALIPAY API: alipay.boss.prod.arrangement.offline.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-03-15 16:47:28
 */
public class AlipayBossProdArrangementOfflineQueryResponse extends AlipayResponse {

	private static final long serialVersionUID = 4256754536245825191L;

	/** 
	 * 商户的签约状态
	 */
	@ApiField("sign_status")
	private String signStatus;

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	public String getSignStatus( ) {
		return this.signStatus;
	}

}
