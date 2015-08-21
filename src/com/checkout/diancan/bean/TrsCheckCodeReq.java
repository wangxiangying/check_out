/** 
 * @author mah
 * @version 创建时间：2015年4月29日 下午8:45:54 
 * 类说明 
*/  
package com.checkout.diancan.bean;

import java.io.Serializable;

/**
 * @author mah
 * @version 创建时间：2015年4月29日 下午8:45:54 
 * 获取验证码请求参数
 */
public class TrsCheckCodeReq implements Serializable{
	private String service;
	private String service_version;
	private String input_charset;
	private String sign_type;
	private String sign;
	/**
	 * 交易模式 固定值：VALID_PAYMENT_INFO
	 */
	private String trans_mode;
	private String partner;
	/**
	 * 商户订单号
	 */
	private String out_trade_no;
	private String currency;
	private String total_fee;
	private String submit_time;
	private String id_type;
	private String id_no;
	private String acct_name;
	private String card_no;
	/**
	 * 固定值：C-信用卡；D-借记卡
	 */
	private String card_type;
	private String valid_date;
	private String bank_name;
	private String cvv;
	private String phone;
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
	/**
	 * @return the submit_time
	 */
	public String getSubmit_time() {
		return submit_time;
	}
	/**
	 * @param submit_time the submit_time to set
	 */
	public void setSubmit_time(String submit_time) {
		this.submit_time = submit_time;
	}
	/**
	 * @return the id_type
	 */
	public String getId_type() {
		return id_type;
	}
	/**
	 * @param id_type the id_type to set
	 */
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}
	/**
	 * @return the id_no
	 */
	public String getId_no() {
		return id_no;
	}
	/**
	 * @param id_no the id_no to set
	 */
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	/**
	 * @return the acct_name
	 */
	public String getAcct_name() {
		return acct_name;
	}
	/**
	 * @param acct_name the acct_name to set
	 */
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
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
	 * @return the valid_date
	 */
	public String getValid_date() {
		return valid_date;
	}
	/**
	 * @param valid_date the valid_date to set
	 */
	public void setValid_date(String valid_date) {
		this.valid_date = valid_date;
	}
	/**
	 * @return the cvv
	 */
	public String getCvv() {
		return cvv;
	}
	/**
	 * @param cvv the cvv to set
	 */
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
