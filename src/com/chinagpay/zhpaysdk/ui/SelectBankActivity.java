package com.chinagpay.zhpaysdk.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chinagpay.zhpaysdk.BaseActivity;
import com.chinagpay.zhpaysdk.R;
import com.chinagpay.zhpaysdk.bean.BankInfo;
import com.chinagpay.zhpaysdk.bean.TrsQueryBankListResp;

import java.util.List;

/**
 * Created by test on 2015/6/15.
 */
public class SelectBankActivity extends BaseActivity {

    private ImageView back;
    private TextView sure;
    private TextView savingsCard, creditCard;
    private LinearLayout savingsBanks, creditBanks, banks;
    private TrsQueryBankListResp trsQueryBankListResp;
    private BankItemView saving_selectItem, credit_selectItem;
    private boolean isCreditBanksFirstDraw, isCreditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_bank);
        trsQueryBankListResp = (TrsQueryBankListResp) getIntent().getSerializableExtra("data");
        isCreditBanksFirstDraw = true;
        initLayout();
        if (trsQueryBankListResp != null) {
            initBanks(trsQueryBankListResp.getDebit_card_list());
        }
    }

    private void initLayout() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sure = (TextView) findViewById(R.id.sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BankInfo bankInfo;
                if (isCreditCard) {
                    if (credit_selectItem == null) {
                        finish();
                        return;
                    }
                    bankInfo = credit_selectItem.getSelectInfo();
                } else {
                    if (saving_selectItem == null) {
                        finish();
                        return;
                    }
                    bankInfo = saving_selectItem.getSelectInfo();
                }
                Intent intent = new Intent();
                intent.putExtra("bankInfo", bankInfo);
                intent.putExtra("isCreditCard", isCreditCard);
                SelectBankActivity.this.setResult(RESULT_OK, intent);
                SelectBankActivity.this.finish();
            }
        });
        savingsCard = (TextView) findViewById(R.id.savingsCard);
        savingsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savingsCard.setBackgroundColor(getResources().getColor(R.color.red_background));
                creditCard.setBackgroundColor(getResources().getColor(R.color.red_line));
                savingsBanks.setVisibility(View.VISIBLE);
                creditBanks.setVisibility(View.GONE);
                isCreditCard = false;
            }
        });
        creditCard = (TextView) findViewById(R.id.creditCard);
        creditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creditCard.setBackgroundColor(getResources().getColor(R.color.red_background));
                savingsCard.setBackgroundColor(getResources().getColor(R.color.red_line));
                savingsBanks.setVisibility(View.GONE);
                creditBanks.setVisibility(View.VISIBLE);
                isCreditCard = true;
                if (isCreditBanksFirstDraw) {
                    isCreditBanksFirstDraw = false;
                    if (trsQueryBankListResp != null) {
                        banks = creditBanks;
                        initBanks(trsQueryBankListResp.getCredit_card_list());
                    }
                }
            }
        });
        savingsBanks = (LinearLayout) findViewById(R.id.savingsBanks);
        creditBanks = (LinearLayout) findViewById(R.id.creditBanks);
        banks = savingsBanks;
    }

    private void initBanks(List<BankInfo> bankInfos) {
        banks.removeAllViews();
        if (bankInfos != null) {
            for (int i = 0; i < bankInfos.size(); i++) {
                BankInfo bankInfo = bankInfos.get(i);
                final BankItemView itemView = new BankItemView(this);
                itemView.initData(bankInfo);
                if (i == 0) {
                    if (isCreditCard) {
                        credit_selectItem = itemView;
                    } else {
                        saving_selectItem = itemView;
                    }
                    itemView.setSelectIconVisible(true);
                }
                itemView.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (isCreditCard) {
                            if (credit_selectItem != null) {
                                credit_selectItem.setSelectIconVisible(false);
                            }
                            credit_selectItem = itemView;
                        } else {
                            if (saving_selectItem != null) {
                                saving_selectItem.setSelectIconVisible(false);
                            }
                            saving_selectItem = itemView;
                        }
                        itemView.setSelectIconVisible(true);
                    }
                });
                banks.addView(itemView);
            }
        }
    }

    public class BankItemView extends LinearLayout {

        public RelativeLayout item;
        private TextView bankName;
        private ImageView select;
        private BankInfo bankInfo;

        public BankItemView(Context context) {
            super(context);
            LayoutInflater.from(context).inflate(R.layout.bank_item, this, true);
            initLayout();
        }

        private void initLayout() {
            item = (RelativeLayout) findViewById(R.id.item);
            bankName = (TextView) findViewById(R.id.bankName);
            select = (ImageView) findViewById(R.id.select);
        }

        public void initData(BankInfo bankInfo) {
            this.bankInfo = bankInfo;
            bankName.setText(bankInfo.getBank_name());
        }

        public BankInfo getSelectInfo() {
            return bankInfo;
        }

        public void setSelectIconVisible(boolean isVisible) {
            if (isVisible) {
                select.setVisibility(VISIBLE);
            } else {
                select.setVisibility(INVISIBLE);
            }
        }
    }
}
