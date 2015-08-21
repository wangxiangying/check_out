package com.checkout.diancan.api;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * api请求返回的结果结构上与HttpResult相似
 *
 * @param <T>
 */
public class ApiResult<T> {
    /**
     * 请求结果 ok 代表返回了正确的结果
     * fail 代表没有返回正确的结果
     */
    public static final int RESULT_OK = 1;
    public static final int RESULT_FAIL = 2;

    /**
     * 请求结果,成功或失败
     */
    private int resultCode;
    /**
     * 错误码
     */
    private int failCode = 0;
    /**
     * 错误信息
     */
    private String failMessage = "";

    private ArrayList entities;
    private Map<String, String> entity;
    private JSONObject jsonEntity;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getFailCode() {
        return failCode;
    }

    public void setFailCode(int failCode) {
        this.failCode = failCode;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public ArrayList getEntities() {
        return entities;
    }

    public void setEntities(ArrayList entities) {
        this.entities = entities;
    }

    public void setEntity(Map<String, String> map) {
        this.entity = map;
    }

    public Map<String, String> getEntity() {
        return entity;
    }

    public JSONObject getJsonEntity() {
        return jsonEntity;
    }

    public void setJsonEntity(JSONObject jsonEntity) {
        this.jsonEntity = jsonEntity;
    }


}
