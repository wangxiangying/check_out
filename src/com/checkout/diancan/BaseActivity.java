package com.checkout.diancan;

import android.app.Activity;
import android.app.Dialog;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.checkout.diancan.R;
import com.checkout.diancan.bean.ServiceParam;
import com.checkout.diancan.manager.Const;

public class BaseActivity extends Activity {

    public static final String _layout = "layout";
    public static final String _string = "string";
    public static final String _id = "id";
    private Dialog dialog;
    private TextView loading;

    /**
     * 初始化加载dialog
     *
     * @return
     */
    public Dialog initDialog(String text) {

        if (dialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.loading_dialog, null);
            loading = (TextView) view.findViewById(R.id.loading);
            dialog = new Dialog(this, R.style.MyDialog);
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(false);
        }
        loading.setText(text);
        return dialog;
    }

    public void dialogShow() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public void dialogDismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void back() {
        if (Const.activityList == null || Const.activityList.size() == 1) {
            if (ServiceParam.handler != null) {
                Message message = new Message();
                message.what = ServiceParam.PAYCANCLE;
                ServiceParam.handler.sendMessage(message);
            }
        }
        finish();
    }

    public int getResourceIdByName(String className, String name) {

        String packageName = getPackageName();
        Class r = null;
        int id = 0;
        try {
            r = Class.forName(packageName + ".R");
            Class[] classes = r.getClasses();
            Class desireClass = null;

            for (int i = 0; i < classes.length; i++) {
                if (classes[i].getName().split("\\$")[1].equals(className)) {
                    desireClass = classes[i];
                    break;
                }
            }

            if (desireClass != null)
                id = desireClass.getField(name).getInt(desireClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return id;

    }
}
