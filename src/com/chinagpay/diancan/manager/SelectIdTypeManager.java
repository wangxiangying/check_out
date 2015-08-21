package com.chinagpay.diancan.manager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.chinagpay.zhpaysdk.R;

/**
 * Created by test on 2015/6/15.
 */
public class SelectIdTypeManager {

    private static String[] aryShop;

    public interface MyOnClickListener {
        public void onclick(String type);
    }

    public static void initSelectDilag(Context context, final MyOnClickListener onClickListener) {
        aryShop = context.getResources().getStringArray(R.array.idType);

        new AlertDialog.Builder(context)
                .setItems(R.array.idType, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (aryShop != null) {
                            String type = aryShop[which];
                            onClickListener.onclick(type);
                        }

                    }
                }).show();
    }
}
