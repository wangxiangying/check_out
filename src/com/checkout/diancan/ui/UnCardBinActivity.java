package com.checkout.diancan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.checkout.diancan.BaseActivity;
import com.checkout.diancan.bean.BankInfo;
import com.checkout.diancan.bean.ServiceParam;
import com.checkout.diancan.bean.TrsCreateOrderResp;
import com.checkout.diancan.bean.TrsQueryBankListResp;
import com.checkout.diancan.manager.BankListManager;
import com.checkout.diancan.manager.Const;
import com.checkout.diancan.manager.OrderDetailManager;
import com.checkout.diancan.tools.StringUtils;
import com.checkout.diancan.ui.widget.IdCodeInfoItemView;
import com.checkout.diancan.ui.widget.InfoItemView;
import com.checkout.diancan.R;

/**
 * Created by test on 2015/6/16.
 */
public class UnCardBinActivity extends BaseActivity {

    private ImageView back;
    private TextView amount, order_detail;
    private LinearLayout ID_BANK_INFO_LAYOUT, infos;
    private RelativeLayout agree_layout;
    private IdCodeInfoItemView idCode;
    private InfoItemView cardNo, carType;
    private Button next;
    private TrsCreateOrderResp trsCreateOrderResp;
    private String name, total_fee, out_trade_no, card_number, bankName, cardType, idTYpe, idNo;
    private final static int SELECT_BANK = 10011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);
        Const.activityList.add(this);
        trsCreateOrderResp = (TrsCreateOrderResp) getIntent().getSerializableExtra("data");
        if (trsCreateOrderResp != null) {
            name = getIntent().getStringExtra("name");
            total_fee = trsCreateOrderResp.getTotal_fee();
            out_trade_no = trsCreateOrderResp.getOut_trade_no();
            card_number = getIntent().getStringExtra("cardNo");
            idTYpe = getIntent().getStringExtra("idType");
            idNo = getIntent().getStringExtra("idNo");
        }
        initLayout();
    }

    private void initLayout() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               back();
            }
        });
        amount = (TextView) findViewById(R.id.amount);
        amount.setText(getString(R.string.RMB) + total_fee);
        order_detail = (TextView) findViewById(R.id.order_detail);
        order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDetailManager.orderDetail(UnCardBinActivity.this, initDialog(getString(R.string.loading)), out_trade_no);
            }
        });
        ID_BANK_INFO_LAYOUT = (LinearLayout) findViewById(R.id.ID_BANK_INFO_LAYOUT);
        ID_BANK_INFO_LAYOUT.setVisibility(View.GONE);
        agree_layout = (RelativeLayout) findViewById(R.id.agree_layout);
        agree_layout.setVisibility(View.GONE);
        infos = (LinearLayout) findViewById(R.id.infos);
        initInfos();
        next = (Button) findViewById(R.id.next);
        next.setBackgroundDrawable(getResources().getDrawable(R.drawable.grey_button_round));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtils.isNotNullOrEmpty(bankName)) {
                    idTYpe = idCode.getIdType();
                    idNo = idCode.getIdNo();
                    Intent intent = new Intent();
                    intent.setClass(UnCardBinActivity.this, ZHPayActivity.class);
                    intent.putExtra("data", trsCreateOrderResp);
                    intent.putExtra("isFromBusiness", true);
                    intent.putExtra("name", name);
                    intent.putExtra("idType", idTYpe);
                    intent.putExtra("idNo", idNo);
                    intent.putExtra("cardNo", card_number);
                    intent.putExtra("bankName", bankName);
                    intent.putExtra("cardType", cardType);
                    startActivity(intent);
                } else {
                    Toast.makeText(UnCardBinActivity.this, R.string.select_card_type, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initInfos() {

        idCode = new IdCodeInfoItemView(this);
        idCode.setData(idTYpe, idNo);
        infos.addView(idCode);
        cardNo = new InfoItemView(this);
        cardNo.setCenterLayout(InfoItemView.INFO_CONTENT_TYPE);
        cardNo.setInfo_title(getString(R.string.card_number));
        cardNo.setInfo_content(card_number);
        infos.addView(cardNo);
        carType = new InfoItemView(this);
        carType.setCenterLayout(InfoItemView.INFO_CONTENT_TYPE);
        carType.setInfo_title(getString(R.string.card_type2));
        carType.setInfo_content(getString(R.string.select_card_type));
        carType.setInfo_contentTextColor(getResources().getColor(R.color.edit_hint_color));
        carType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BankListManager.getBankList(UnCardBinActivity.this, initDialog(getString(R.string.loading)), new BankListManager.OnSuccessListener() {
                    @Override
                    public void success(TrsQueryBankListResp trsQueryBankListResp) {
                        Intent intent = new Intent();
                        intent.putExtra("data", trsQueryBankListResp);
                        intent.setClass(UnCardBinActivity.this, SelectBankActivity.class);
                        startActivityForResult(intent, SELECT_BANK);
                    }
                });
            }
        });
        infos.addView(carType);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == SELECT_BANK) {

                    if (data != null) {
                        BankInfo bankInfo = (BankInfo) data.getSerializableExtra("bankInfo");
                        if (bankInfo != null) {
                            boolean isCreditCard = data.getBooleanExtra("isCreditCard", false);
                            Toast.makeText(UnCardBinActivity.this, isCreditCard + bankInfo.getBank_name(), Toast.LENGTH_SHORT).show();
                            carType.setInfo_content(bankInfo.getBank_name());
                            carType.setInfo_contentTextColor(getResources().getColor(R.color.black));
                            bankName = bankInfo.getBank_name();
                            cardType = isCreditCard ? ServiceParam.CardType.CREDIT : ServiceParam.CardType.DEBIT;
                            next.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_round));
                        }
                    }

                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Const.activityList.remove(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        back();
        return super.onKeyDown(keyCode, event);
    }
}
