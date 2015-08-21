package com.checkout.diancan.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.checkout.diancan.R;

/**
 * 支付页面填写信息的item控件
 * Created by test on 2015/5/18.
 */
public class InfoItemView extends LinearLayout {

    public TextView info_title, right_text, right_button, select_text, info_content;
    private ImageView right_image;
    public LinearLayout right_select;
    public EditText info_edit;
    public final static int RIGHT_TEXT_TYPE = 10;
    public final static int RIGHT_BUTTON_TYPE = 11;
    public final static int RIGHT_IMAGE = 12;
    public final static int RIGHT_SELECT = 13;
    public final static int INFO_CONTENT_TYPE = 20;
    public final static int INFO_EDIT_TYPE = 21;

    public InfoItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.info_item, this, true);
        initLayout();
    }

    public InfoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.info_item, this, true);
        initLayout();
    }

    /**
     * 初始化控件
     */
    private void initLayout() {
        info_title = (TextView) findViewById(R.id.info_title);
        right_text = (TextView) findViewById(R.id.right_text);
        right_button = (TextView) findViewById(R.id.right_button);
        select_text = (TextView) findViewById(R.id.select_text);
        info_content = (TextView) findViewById(R.id.info_content);
        right_image = (ImageView) findViewById(R.id.right_image);
        right_select = (LinearLayout) findViewById(R.id.right_select);
        info_edit = (EditText) findViewById(R.id.info_edit);
    }

    /**
     * 设置左边标题
     *
     * @param text
     */
    public void setInfo_title(String text) {
        info_title.setText(text);
    }

    /**
     * 设置中间内容
     *
     * @param text
     */
    public void setInfo_content(String text) {
        info_content.setText(text);
    }

    /**
     * 设置中间内容字体颜色
     *
     * @param color
     */
    public void setInfo_contentTextColor(int color) {
        info_content.setTextColor(color);
    }

    /**
     * 设置中间输入hint
     *
     * @param text
     */
    public void setInfo_editHintText(String text) {
        info_edit.setHint(text);
    }

    /**
     * 设置右边控件
     */
    public void setRightLayout(int type) {

        switch (type) {
            case RIGHT_TEXT_TYPE:
                right_text.setVisibility(VISIBLE);
                break;
            case RIGHT_BUTTON_TYPE:
                right_button.setVisibility(VISIBLE);
                break;
            case RIGHT_IMAGE:
                right_image.setVisibility(VISIBLE);
                break;
            case RIGHT_SELECT:
                right_select.setVisibility(VISIBLE);
                break;
        }
    }

    /**
     * 设置中间控件
     *
     * @param type
     */
    public void setCenterLayout(int type) {
        switch (type) {
            case INFO_CONTENT_TYPE:
                info_content.setVisibility(VISIBLE);
                info_edit.setVisibility(GONE);
                break;
            case INFO_EDIT_TYPE:
                info_edit.setVisibility(VISIBLE);
                info_content.setVisibility(GONE);
                break;
        }
    }

    /**
     * 获取输入edit内容
     *
     * @return
     */
    public String getEditText() {
        return info_edit.getText().toString();
    }

    /**
     * 设置选择证件类别
     *
     * @param text
     */
    public void setSelect_text(String text) {
        select_text.setText(text);
    }

    /**
     * 获取证件类别
     *
     * @return
     */
    public String getSelectText() {
        return select_text.getText().toString();
    }
}
