package com.chinagpay.diancan.ui.framework.pay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chinagpay.zhpaysdk.R;

/**
 * Created by Administrator on 2015/6/30.
 */
public class AmountLayout extends LinearLayout {

    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

    RelativeLayout.LayoutParams rww = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

   public TextView amount_text,custom_order_detail,amount,order_detail,beneficiary;
    public  RelativeLayout amount_bottom,amount_title;
    public  LinearLayout amount_benefit;

    public AmountLayout(Context context) {
        super(context);
        initLayout();
    }

    public AmountLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    private void initLayout() {

        setBackgroundColor(getContext().getResources().getColor(R.color.red_background));
        setOrientation(VERTICAL);

        amount_title = new RelativeLayout(getContext());
        amount_title.setPadding(15,15,15,15);


        amount_text=new TextView(getContext());
        amount_text.setText(getContext().getString(R.string.amount_dollar));
        amount_text.setTextColor(getContext().getResources().getColor(R.color.red_word_color));
        amount_title.addView(amount_text,lp2);


        RelativeLayout.LayoutParams lp3  = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        custom_order_detail=new TextView(getContext());
        custom_order_detail.setText(getContext().getString(R.string.order_detail));
        custom_order_detail.setTextColor(getContext().getResources().getColor(R.color.white));
        custom_order_detail.setBackgroundResource(R.drawable.order_detail_round);
        custom_order_detail.setTextSize(14);
        custom_order_detail.setGravity(Gravity.CENTER);
        custom_order_detail.setPadding(14,5,14,5);
        custom_order_detail.setVisibility(GONE);
        amount_title.addView(custom_order_detail,lp3);

        amount=new TextView(getContext());
        amount.setTextSize(40);
        amount.setText("?90.85");
        amount.setTextColor(getContext().getResources().getColor(R.color.white));
        amount.setGravity(Gravity.CENTER);
        lp2.gravity = Gravity.CENTER;
        amount.setLayoutParams(lp2);



        amount_bottom=new RelativeLayout(getContext());
        amount_bottom.setPadding(15,15,15,15);

        order_detail=new TextView(getContext());
        rww.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        order_detail.setBackgroundResource(R.drawable.order_detail_round);
        order_detail.setText(getContext().getString(R.string.order_detail));
        order_detail.setTextSize(14);
        order_detail.setTextColor(getContext().getResources().getColor(R.color.white));
        order_detail.setGravity(Gravity.CENTER);
        order_detail.setPadding(15,5,15,5);
        rww.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
        amount_bottom.addView(order_detail,rww);


        amount_benefit=new LinearLayout(getContext());
        amount_benefit.setOrientation(VERTICAL);
        amount_benefit.setPadding(0,20,0,0);
        amount_benefit.setVisibility(GONE);

        TextView tempT=new TextView(getContext());
        LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
        tempT.setBackgroundColor(getContext().getResources().getColor(R.color.red_line));


        amount_benefit.addView(tempT,lp4);
        beneficiary=new TextView(getContext());
        beneficiary=new TextView(getContext());
        LinearLayout.LayoutParams lp5= new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        beneficiary.setPadding(15,15,15,15);
        beneficiary.setText(getContext().getString(R.string.Beneficiary));
        beneficiary.setTextColor(getContext().getResources().getColor(R.color.white));
        beneficiary.setTextSize(14);
        amount_benefit.addView(beneficiary,lp5);

        addView(amount_title,lp);
        addView(amount, lp2);
        addView(amount_bottom, lp2);
        addView(amount_benefit,lp2);
    }

}
