package com.checkout.diancan.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class GsonUtils {

    public static Gson newInstance() {
        Gson gson = (new GsonBuilder()).disableHtmlEscaping().create();
        return gson;
    }

//	/**
//	 * json è½¬æ�¢æˆ�httpçš„å­—ç¬¦ä¸²
//	 *
//	 * @param jsonStr
//	 * @return
//	 */
//	public static String jsonToHttpStr(String jsonStr) {
//		// JsonObject js=JSONObject.toBean(JSONObject.fromObject(jsonStr));
//		JSONObject js = JSONObject.fromObject(jsonStr);
//		Iterator it = js.keys();
//		StringBuffer str = new StringBuffer();
//		while (it.hasNext()) {
//			String key = String.valueOf(it.next());
//			String value = (String) js.get(key);
//			if (value == null || value.equals("null"))
//				value = "";
//			str = str.append(key).append("=").append(value).append("&");
//		}
//		str = str.delete(str.length() - 1, str.length());
//		return str.toString();
//	}
//
//	/**
//	 * json è½¬æ�¢æˆ�mapçš„å­—ç¬¦ä¸²
//	 *
//	 * @param jsonStr
//	 * @return
//	 */
//	public static Map<String, String> jsonToMap(String jsonStr) {
//		// JsonObject js=JSONObject.toBean(JSONObject.fromObject(jsonStr));
//		JSONObject js = JSONObject.fromObject(jsonStr);
//		Iterator it = js.keys();
//		Map<String, String> str = new HashMap<String, String>();
//		while (it.hasNext()) {
//			String key = String.valueOf(it.next());
//			String value = (String) js.get(key);
//			str.put(key, value);
//
//		}
//		return str;
//	}

    /**
     * json
     *
     * @return
     */
    public static String[] jsonToArray(Object obj) {
        // JsonObject js=JSONObject.toBean(JSONObject.fromObject(jsonStr));


        JSONObject js = objectToJsonObject(obj);
        String[] str = jsonObjectToArr(js);
        return str;

    }

    /**
     * JsonObject 转成 字符串数组
     *
     * @param js
     * @return
     */
    public static String[] jsonObjectToArr(JSONObject js) {
        if (js != null) {
            Iterator it = js.keys();
            String[] str = new String[js.length()];
            int x = 0;
            while (it.hasNext()) {
                String key = String.valueOf(it.next());
                String value = null;
                try {
                    value = (String) js.get(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (value == null || value.equals("null"))
                    value = "";
                str[x] = key + "=" + value;
                x++;
            }
            return str;
        }

        return null;

    }

    /**
     * Object转成JSONObject类型
     *
     * @param obj
     * @return
     */
    public static JSONObject objectToJsonObject(Object obj) {
        if (obj != null) {
            Gson gson = new Gson();
            String json = gson.toJson(obj);
            try {
                JSONObject js = new JSONObject(json);
                return js;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
