package com.chinagpay.zhpaysdk.bean;

import java.io.Serializable;

public class TrsConfirmPaymentResp implements Serializable {
	private String service;
	private String service_version;
	private String sign_type;
	private String sign;
	private String ret_method;
	private String ret_code;
	private String ret_msg;
	private String partner;
	private String partner_name;
	private String out_trade_no;
	private String currency;
	private String total_fee;
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
	/**
	 * @return the partner_name
	 */
	public String getPartner_name() {
		return partner_name;
	}
	/**
	 * @param partner_name the partner_name to set
	 */
	public void setPartner_name(String partner_name) {
		this.partner_name = partner_name;
	}
	/**
	 * @return the out_trade_no
	 */
	public String getOut_trade_no() {
		return out_trade_no;
	}
	/**
	 * @param out_trade_no the out_trade_no to set
	 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the total_fee
	 */
	public String getTotal_fee() {
		return total_fee;
	}
	/**
	 * @param total_fee the total_fee to set
	 */
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

}
