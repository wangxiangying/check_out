package com.chinagpay.diancan.ui.framework.pay;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.*;
import com.chinagpay.zhpaysdk.R;

/**
 * Created by Administrator on 2015/6/30.
 */
public class Title extends RelativeLayout {

    public ImageView back, more;
    public TextView center_text;

    public Title(Context context) {
        super(context);
        initLayout();
    }

    public Title(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    private void initLayout() {
        this.setPadding(15, 15, 15, 15);
        setBackgroundColor(Color.WHITE);

        back = new ImageView(getContext());
        back.setBackgroundResource(R.drawable.back);
        addView(back);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        back.setLayoutParams(lp);


        center_text = new TextView(getContext());
        center_text.setText(getContext().getString(R.string.ZH_PAY));
        center_text.setTextSize(18);
        center_text.setTextColor(getContext().getResources().getColor(R.color.title_black));
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp3.addRule(RelativeLayout.BELOW, back.getId());
        lp3.addRule(RelativeLayout.CENTER_IN_PARENT);
        center_text.setLayoutParams(lp3);
        addView(center_text);


        more = new ImageView(getContext());
        more.setBackgroundResource(R.drawable.more);
        addView(more);
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        lp2.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        more.setLayoutParams(lp2);

    }
}
