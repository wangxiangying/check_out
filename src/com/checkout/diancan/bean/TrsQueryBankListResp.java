package com.checkout.diancan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author mah
 * @version 创建时间：2015年5月5日 上午11:08:12 类说明
 *          查询银行卡列表返回
 */
public class TrsQueryBankListResp implements Serializable {
    private String partner;
    private List<BankInfo> credit_card_list;
    private List<BankInfo> debit_card_list;

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
     * @return the credit_card_list
     */
    public List<BankInfo> getCredit_card_list() {
        return credit_card_list;
    }

    /**
     * @param credit_card_list the credit_card_list to set
     */
    public void setCredit_card_list(List<BankInfo> credit_card_list) {
        this.credit_card_list = credit_card_list;
    }

    /**
     * @return the debit_card_list
     */
    public List<BankInfo> getDebit_card_list() {
        return debit_card_list;
    }

    /**
     * @param debit_card_list the debit_card_list to set
     */
    public void setDebit_card_list(List<BankInfo> debit_card_list) {
        this.debit_card_list = debit_card_list;
    }

}
