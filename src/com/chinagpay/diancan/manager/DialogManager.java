package com.chinagpay.diancan.manager;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.chinagpay.diancan.tools.StringUtils;
import com.chinagpay.zhpaysdk.R;

/**
 * Created by test on 2015/6/15.
 */
public class DialogManager {

    private static Dialog dialog2, dialog3, dialog4;
    private static TextView notice2_title, notice2_content, notice3_title, notice3_content, notice4TextView;

    /**
     * PG-PUB-NOTICE1
     *
     * @param context
     * @param notice
     */
    public void notice1Show(Context context, String notice) {
        Toast.makeText(context, notice, Toast.LENGTH_SHORT).show();
    }

    /**
     * PG-PUB-NOTICE2
     *
     * @param context
     * @param title
     * @param content
     */
    public static void notice2Show(Context context, String title, String content) {
        if (dialog2 == null) {
            View view = LayoutInflater.from(context).inflate(R.layout.notice2_dialog, null);
            notice2_title = (TextView) view.findViewById(R.id.notice2_title);
            notice2_content = (TextView) view.findViewById(R.id.notice2_content);
            ImageView close = (ImageView) view.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog2.dismiss();
                }
            });
            dialog2 = new Dialog(context, R.style.MyDialog);
            dialog2.setContentView(view);
            dialog2.setCanceledOnTouchOutside(false);
        }
        notice2_title.setText(title);
        notice2_content.setText(content);
        dialog2.show();

    }


    /**
     * PG-PUB-NOTICE3
     *
     * @param context
     * @param title
     * @param content
     */
    public static void notice3Show(Context context, String title, String content) {
        if (dialog3 == null) {
            View view = LayoutInflater.from(context).inflate(R.layout.notice3_dialog, null);
            notice3_title = (TextView) view.findViewById(R.id.notice3_title);
            notice3_content = (TextView) view.findViewById(R.id.notice3_content);
            TextView sure = (TextView) view.findViewById(R.id.sure);
            sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog3.dismiss();
                }
            });
            dialog3 = new Dialog(context, R.style.MyDialog);
            dialog3.setContentView(view);
            dialog3.setCanceledOnTouchOutside(false);
        }
        if (StringUtils.isNotNullOrEmpty(title)) {
            notice3_title.setVisibility(View.VISIBLE);
            notice3_title.setText(title);
        } else {
            notice3_title.setVisibility(View.GONE);
        }

        if (StringUtils.isNotNullOrEmpty(content)) {
            notice3_content.setVisibility(View.VISIBLE);
            notice3_content.setText(content);
        }else {
            notice3_content.setVisibility(View.GONE);
        }
        dialog3.show();

    }

    /**
     * PG-PUB-NOTICE4
     *
     * @param context
     * @param notice
     */
    public static void notice4Show(Context context, String notice) {
        if (dialog4 == null) {
            View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null);
            notice4TextView = (TextView) view.findViewById(R.id.loading);
            dialog4 = new Dialog(context, R.style.MyDialog);
            dialog4.setContentView(view);
            dialog4.setCanceledOnTouchOutside(false);
        }
        notice4TextView.setText(notice);
        dialog4.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog4.dismiss();
            }
        }, 4000);
    }
}
