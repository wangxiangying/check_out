/**
 * @author mah
 * @version 创建时间：2015年5月5日 上午11:10:16 
 * 类说明 
 */
package com.checkout.diancan.bean;

import java.io.Serializable;

/**
 * @author mah
 * @version 创建时间：2015年5月5日 上午11:10:16
 *          查询卡信息list的bean
 */
public class BankInfo implements Serializable {
    private String bank_name;
    private String bank_info;

    /**
     * @return the bank_name
     */
    public String getBank_name() {
        return bank_name;
    }

    public BankInfo(String bank_name, String bank_info) {

        this.bank_name = bank_name;
        this.bank_info = bank_info;
    }

    /**
     * @param bank_name the bank_name to set
     */
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    /**
     * @return the bank_info
     */
    public String getBank_info() {
        return bank_info;
    }

    /**
     * @param bank_info the bank_info to set
     */
    public void setBank_info(String bank_info) {
        this.bank_info = bank_info;
    }


}
