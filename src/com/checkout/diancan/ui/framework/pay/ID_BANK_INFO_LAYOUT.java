package com.checkout.diancan.ui.framework.pay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.checkout.diancan.ui.widget.IdCodeInfoItemView;
import com.checkout.diancan.R;

/**
 * Created by Administrator on 2015/7/2.
 */
public class ID_BANK_INFO_LAYOUT extends LinearLayout {

    public IdCodeInfoItemView idcode_item;
    LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    LayoutParams lp2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 50);
    LayoutParams lp3 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 40);
    TextView bankCard;

    public ID_BANK_INFO_LAYOUT(Context context) {
        super(context);
        initLayout();
    }

    public ID_BANK_INFO_LAYOUT(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public void initLayout() {
        setOrientation(VERTICAL);
        idcode_item = new IdCodeInfoItemView(getContext());

        bankCard = new TextView(getContext());
        bankCard.setBackgroundColor(getContext().getResources().getColor(R.color.white));
        bankCard.setTextSize(14);
        bankCard.setTextColor(getContext().getResources().getColor(R.color.info_item_title));
        bankCard.setGravity(Gravity.CENTER | Gravity.LEFT);
        bankCard.setPadding(15, 0, 0, 0);


        TextView tempT = new TextView(getContext());
        tempT.setTextSize(14);
        tempT.setTextColor(getContext().getResources().getColor(R.color.grey_text));
        tempT.setGravity(Gravity.CENTER | Gravity.LEFT);
        tempT.setPadding(15, 0, 0, 0);


        addView(idcode_item, lp);
        addView(bankCard, lp2);
        addView(tempT, lp3);

    }


}
