package com.chinagpay.diancan.ui.framework.pay;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.*;
import com.chinagpay.zhpaysdk.R;

/**
 * Created by Administrator on 2015/6/30.
 */


//��Ӧ R.layout.pay

public class CustomPayLayout extends LinearLayout {


    public LinearLayout layout, layoutTitle, layoutAmount, infos;
    ScrollView.LayoutParams lp = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.MATCH_PARENT);
    LayoutParams lp2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    LayoutParams lp3 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    LayoutParams lp4 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    LayoutParams lp5 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 150);
    public Title title;
    public ScrollView scrollView;
    public AmountLayout amount_layout;
    public ID_BANK_INFO_LAYOUT id_bank_info_layout;
    public AgreeLayout agree_layout;
    public Button next;


    public CustomPayLayout(Context context) {
        super(context);
        initLayout();
    }

    public CustomPayLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    private void initLayout() {

        setBackgroundColor(getContext().getResources().getColor(R.color.white));
        scrollView = new ScrollView(getContext());

        layout = new LinearLayout(getContext());
        layout.setBackgroundColor(getContext().getResources().getColor(R.color.white));
        layout.setOrientation(VERTICAL);


        layoutTitle = new LinearLayout(getContext());

        layoutTitle.setBackgroundColor(getContext().getResources().getColor(R.color.white));

        title = new Title(getContext());

        layoutAmount = new LinearLayout(getContext());
  
        amount_layout = new AmountLayout(getContext());

        id_bank_info_layout = new ID_BANK_INFO_LAYOUT(getContext());

        infos = new LinearLayout(getContext());
        infos.setOrientation(VERTICAL);

        agree_layout = new AgreeLayout(getContext());

        next=new Button(getContext());
        lp5.setMargins(15,0,15,0);
        next.setLayoutParams(lp5);
        next.setBackgroundResource(R.drawable.red_button_round);
        next.setTextColor(getContext().getResources().getColor(R.color.white));
        next.setTextSize(18);
        next.setText(getContext().getString(R.string.next));


        addView(scrollView, lp);
        scrollView.addView(layout, lp2);
        layout.addView(layoutTitle, lp3);
        layoutTitle.addView(title);
        layout.addView(layoutAmount, lp3);
        layoutAmount.addView(amount_layout);
        layout.addView(id_bank_info_layout, lp4);
        layout.addView(infos, lp4);
        layout.addView(agree_layout, lp4);
        layout.addView(next, lp5);

    }
}
