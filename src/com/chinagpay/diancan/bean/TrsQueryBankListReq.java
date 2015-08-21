package com.chinagpay.diancan.bean;
 /** 
 * @author mah
 * @version 创建时间：2015年5月5日 上午11:07:50 
 *  查询银行卡列表请求
 */
public class TrsQueryBankListReq {

	private String service;
	private String service_version;
	private String input_charset;
	private String sign_type;
	private String sign;
	private String trans_mode;
	private String partner;
	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}
	/**
	 * @return the service_version
	 */
	public String getService_version() {
		return service_version;
	}
	/**
	 * @param service_version the service_version to set
	 */
	public void setService_version(String service_version) {
		this.service_version = service_version;
	}
	/**
	 * @return the input_charset
	 */
	public String getInput_charset() {
		return input_charset;
	}
	/**
	 * @param input_charset the input_charset to set
	 */
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	/**
	 * @return the sign_type
	 */
	public String getSign_type() {
		return sign_type;
	}
	/**
	 * @param sign_type the sign_type to set
	 */
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * @return the trans_mode
	 */
	public String getTrans_mode() {
		return trans_mode;
	}
	/**
	 * @param trans_mode the trans_mode to set
	 */
	public void setTrans_mode(String trans_mode) {
		this.trans_mode = trans_mode;
	}
	/**
	 * @return the partner
	 */
	public String getPartner() {
		return partner;
	}
	/**
	 * @param partner the partner to set
	 */
	public void setPartner(String partner) {
		this.partner = partner;
	}

}
