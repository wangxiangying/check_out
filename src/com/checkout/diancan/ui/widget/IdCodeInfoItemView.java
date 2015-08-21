package com.checkout.diancan.ui.widget;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import com.checkout.diancan.manager.SelectIdTypeManager;
import com.checkout.diancan.tools.StringUtils;
import com.checkout.diancan.bean.ServiceParam;

/**
 * Created by test on 2015/6/16.
 */
public class IdCodeInfoItemView extends InfoItemView {

    private String idType, idNo;

    public IdCodeInfoItemView(Context context) {
        super(context);
        init();
    }

    public IdCodeInfoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {

        setCenterLayout(InfoItemView.INFO_CONTENT_TYPE);
        setRightLayout(InfoItemView.RIGHT_TEXT_TYPE);
        right_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initUpdateIdView();
            }
        });
    }

    /**
     * 设置数据：卡类型，卡号
     *
     * @param idType 卡类型
     * @param idNo   卡号
     */
    public void setData(String idType, String idNo) {
        this.idType = idType;
        this.idNo = idNo;
        setInfo_title(ServiceParam.IDType.getTypeSmallNameFromKey(idType));
        if (StringUtils.isNotNullOrEmpty(idNo) && idNo.length() > 4) {
            setInfo_content(idNo.substring(0, 3) + "******" + idNo.substring(idNo.length() - 4, idNo.length()));
        }
    }

    private void initUpdateIdView() {
        info_title.setEnabled(true);
        info_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectIdTypeManager.initSelectDilag(getContext(), new SelectIdTypeManager.MyOnClickListener() {

                    @Override
                    public void onclick(String type) {
                        idType = ServiceParam.IDType.getKeyFromValue(type);
                        setInfo_title(ServiceParam.IDType.getTypeSmallNameFromKey(idType));
                    }
                });
            }
        });
        setCenterLayout(InfoItemView.INFO_EDIT_TYPE);
        info_edit.setText(idNo);
        info_edit.setSelection(idNo.length());
        info_edit.setInputType(InputType.TYPE_CLASS_PHONE);
        info_edit.requestFocus();
        info_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                idNo = s.toString();
                if (StringUtils.isNotNullOrEmpty(s.toString())) {

                } else {

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }

    public String getIdType() {
        return idType;
    }

    public String getIdNo() {
        return idNo;
    }

}
