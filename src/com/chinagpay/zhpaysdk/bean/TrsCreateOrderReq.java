/**
 * @author mah
 * @version 创建时间：2015年5月1日 下午1:58:53 
 * 类说明 
 */
package com.chinagpay.zhpaysdk.bean;

/**
 * @author mah
 * @version 创建时间：2015年5月1日 下午1:58:53
 *          创建商户订单的bean
 */
public class TrsCreateOrderReq {
    private String service;
    private String service_version;
    private String input_charset;
    private String sign_type;
    private String sign;
    /**
     * NORMAL_MODE标准支付方式。SIMPLE_MODE绑卡支付模式。
     */
    private String trans_mode;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 境外商户号12位正整数
     */
    private String partner;
    /**
     * 接收通知的URL，需给绝对路径
     */
    private String notify_url;
    /**
     * 商户系统订单交易号,可包含字母,确保在商户系统中唯一。
     */
    private String out_trade_no;
    /**
     * 币种
     */
    private String currency;
    /**
     * 交易金额，保留小数点后两位
     */
    private String total_fee;
    /**
     * 订单生成时间 yyyyMMddhhmmss
     */
    private String time_start;
    /**
     * 订单失效时间 yyyyMMddhhmmss (非必填)
     */
    private String time_expire;
    /**
     * 证件类型(trans_mode为SIMPLE_MODE时必填。)
     */
    private String id_type;
    /**
     * 证件号(trans_mode为SIMPLE_MODE时必填。)
     */
    private String id_no;
    /**
     * 银行账户开户姓名(trans_mode为SIMPLE_MODE时必填。)
     */
    private String acct_name;
    /**
     * 银行卡号或银行卡编号(trans_mode为SIMPLE_MODE时必填。)
     */
    private String card_no;
    /**
     * 卡户行(trans_mode为SIMPLE_MODE时必填。)
     */
    private String bank_name;
    /**
     * 附加数据(非必填)
     */
    private String attach;


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
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
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
     * @return the notify_url
     */
    public String getNotify_url() {
        return notify_url;
    }

    /**
     * @param notify_url the notify_url to set
     */
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
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
     * @return the time_start
     */
    public String getTime_start() {
        return time_start;
    }

    /**
     * @param time_start the time_start to set
     */
    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    /**
     * @return the time_expire
     */
    public String getTime_expire() {
        return time_expire;
    }

    /**
     * @param time_expire the time_expire to set
     */
    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
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
     * @return the attach
     */
    public String getAttach() {
        return attach;
    }

    /**
     * @param attach the attach to set
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }


}
