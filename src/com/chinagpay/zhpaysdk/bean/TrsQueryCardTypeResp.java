package com.chinagpay.zhpaysdk.bean;

import java.io.Serializable;

/**
 * 
 * @author mah
 * @version 创建时间：2015年4月17日 上午11:48:16 
 *卡bin信息的返回
 */
public class TrsQueryCardTypeResp implements Serializable{
	private String service;
	private String service_version;
	private String input_charset;
	private String sign_type;
	private String sign;
	private String ret_method;
	private String ret_code;
	private String ret_msg;
	private String card_no;
	private String card_type;
	private String bank_name;
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
	 * @return the ret_method
	 */
	public String getRet_method() {
		return ret_method;
	}
	/**
	 * @param ret_method the ret_method to set
	 */
	public void setRet_method(String ret_method) {
		this.ret_method = ret_method;
	}
	/**
	 * @return the ret_code
	 */
	public String getRet_code() {
		return ret_code;
	}
	/**
	 * @param ret_code the ret_code to set
	 */
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	/**
	 * @return the ret_msg
	 */
	public String getRet_msg() {
		return ret_msg;
	}
	/**
	 * @param ret_msg the ret_msg to set
	 */
	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}
	/**
	 * @return the card_no
	 */
	public String getCard_no() {
		return card_no;
	}
	/**
	 * @param card_no the card_no to set
	 */
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	/**
	 * @return the card_type
	 */
	public String getCard_type() {
		return card_type;
	}
	/**
	 * @param card_type the card_type to set
	 */
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	/**
	 * @return the bank_name
	 */
	public String getBank_name() {
		return bank_name;
	}
	/**
	 * @param bank_name the bank_name to set
	 */
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}


}
