package com.chinagpay.diancan.ui.framework.pay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chinagpay.zhpaysdk.R;

/**
 * Created by Administrator on 2015/7/2.
 */
public class AgreeLayout extends RelativeLayout {

    LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
    LayoutParams lp2=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);

    public TextView agree_pay_tips;
    public AgreeLayout(Context context) {
        super(context);
        initLayout();
    }

    public AgreeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    private void initLayout() {

        TextView tempT=new TextView(getContext());
        tempT.setTextColor(getResources().getColor(R.color.info_item_title));
        tempT.setTextSize(14);
        tempT.setText(getContext().getString(R.string.agree_pay));
        tempT.setPadding(15,0,0,0);
        tempT.setGravity(Gravity.CENTER);

        agree_pay_tips=new TextView(getContext());
        agree_pay_tips.setTextColor(getContext().getResources().getColor(R.color.info_item_title));



        lp2.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        agree_pay_tips.setLayoutParams(lp2);
        agree_pay_tips.setTextSize(14);
        agree_pay_tips.setText(getContext().getString(R.string.agree_pay_tips));
        agree_pay_tips.setPadding(0,0,15,0);
        agree_pay_tips.setGravity(Gravity.CENTER);


        addView(tempT, lp);
        addView(agree_pay_tips, lp2);

    }


}
