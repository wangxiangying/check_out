package com.chinagpay.diancan.api;

/**
 * Created by king on 2015/6/9.
 */

import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.*;

public class JsonFormatTool {
    private String TAG = "JsonFormatTool";
    private String tempLastChar = "";
    private int templevel = 0;
    private final int tokenCount = 3;
    private String token = ".";


    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public void formatJsonPrint(String TAG, String json) {
        try {

            long start = System.currentTimeMillis();
            setTAG(TAG);
            Gson gson = new Gson();
            if (json.trim().startsWith("{")) {
                Type lt = new TypeToken<LinkedHashMap<String, Object>>() {
                }.getType();
                LinkedHashMap<String, Object> map = gson.fromJson(json, lt);
                printJson(mapSort(map), 0);
            } else if (json.trim().startsWith("[")) {
                Type lt = new TypeToken<ArrayList<Object>>() {
                }.getType();
                ArrayList<Object> map = gson.fromJson(json, lt);
                printArrayList(map, 0);

            } else {
                Log.e("JSON", "JSON PARSER ERROR not start with { or [");
            }
            long end = System.currentTimeMillis();
            Log.e("time spend    "," xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx "+(end-start) +"");


        } catch (Throwable ex) {
            Log.e("JSON", "JSON PARSER ERROR");
            ex.printStackTrace();
        }
    }

    private LinkedHashMap<String, Object> mapSort(LinkedHashMap<String, Object> map) {
        List<Map.Entry<String, Object>> lists = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
        Collections.sort(lists, new Comparator<Map.Entry<String, Object>>() {
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return getFristAsccii(o1.getKey()) - getFristAsccii(o2.getKey());
            }
        });
        LinkedHashMap<String, Object> newMap = new LinkedHashMap<String, Object>();
        for (Map.Entry<String, Object> entity : lists) {
            newMap.put(entity.getKey(), entity.getValue());
        }
        return newMap;
    }

    private void printArrayList(Object arrayList, int level) {
        level++;
        Log.e(TAG, printSpaceToken(level) + "   [");
        if (arrayList instanceof ArrayList) {
            ArrayList arrayList1 = (ArrayList) arrayList;
            for (Object val : arrayList1) {
                if (val instanceof LinkedHashMap) {
                    printJson(mapSort((LinkedHashMap) val), level);
                } else if (val instanceof ArrayList) {
                    printArrayList(val, level);
                } else {
                    print(null, val, level);
                }
            }
        } else if (arrayList instanceof LinkedHashMap) {
            LinkedHashMap arrayList1 = (LinkedHashMap) arrayList;
            printJson(mapSort((LinkedHashMap) arrayList1), level);

        } else {
            print(null, arrayList, level);
        }

        Log.e(TAG, printSpaceToken(level) + "   ]");
    }

    private void printJson(LinkedHashMap temp, int level) {
        level++;
//        System.out.println(printSpaceToken(level) + "   {");
        Log.e(TAG, printSpaceToken(level) + "   {");
        Iterator iter = temp.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            Object val = entry.getValue();
            if (val instanceof LinkedHashMap) {
                print(key, "", level);
                printJson(mapSort((LinkedHashMap) val), level);
            } else if (val instanceof ArrayList) {
                print(key, "", level);
                printArrayList((ArrayList) val, level);
            } else {
                print(key, val, level);
            }
        }
        Log.e(TAG, printSpaceToken(level) + "   }");
    }

    private String printSpaceToken(int count) {
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < count; j++) {
            sb.append(tokenCreate(tokenCount));
        }
        return sb.toString();
    }

    private String tokenCreate(int size) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                sb.append(token);
            } else {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private String spaceCreate(int size) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private int getFristAsccii(String s) {
        if (!TextUtils.isEmpty(s)) {
            return (int) s.toCharArray()[0];
        } else {
            return 0;
        }
    }

    private void print(String key, Object val, int level) {
        String   leftToken = "";
        StringBuffer show=new StringBuffer();
        if (val == null) {
            val = "null";
        }
        if (!TextUtils.isEmpty(key)) {
            String fv = key.substring(0, 1);
            if (fv.equals(tempLastChar) && templevel == level) {
                leftToken = tokenCreate(tokenCount);
            } else {
                leftToken = makeString(fv, spaceCreate(tokenCount - 1));
            }
        }
        if (val instanceof String) {
            if (TextUtils.isEmpty(key)) {
                String strVal = (String) val;
                if (TextUtils.isEmpty(strVal)) {
                 show.append(printSpaceToken(level)).append("   \"\"");
                } else {
                    show.append( printSpaceToken(level)).append("    \"").append(((String) val).replace("\n", "")).append( "\"");
//                  show =     printSpaceToken(level) + "    \"" + ((String) val).replace("\n", "") + "\"";
//                    show =   makeString(printSpaceToken(level ), "    \"" ,((String) val).replace("\n", ""),"\"");
                }
            } else {
                String strVal = (String) val;
                if (TextUtils.isEmpty(strVal)) {

                    show.append(printSpaceToken(level - 1)).append(leftToken).append("   \"").append(key).append("\"");

//                    show = printSpaceToken(level - 1) + leftToken + "   \"" + key + "\" : ";

//                    show =   makeString(printSpaceToken(level- 1 ), leftToken,"   \"",key,"\" : " );

                } else {

                    show.append(printSpaceToken(level - 1)).append(leftToken).append("   \"").append(key).append( "\" : \"").append(strVal.replace("\n", "")).append("\"");

//                    show = printSpaceToken(level - 1) + leftToken + "   \"" + key + "\" : \"" + strVal.replace("\n", "") + "\"";

//                    show =   makeString(printSpaceToken(level - 1),leftToken,"   \"",key,"\" : \"",strVal.replace("\n", ""),  "\"");

                }
            }

        } else if (val instanceof Double) {
            Double temp = (Double) val;
            DecimalFormat decimalFormat = new DecimalFormat("###0.00");//格式化设置
            String decimal = decimalFormat.format(temp).replace(".00","");
            if (TextUtils.isEmpty(key)) {

                show.append( printSpaceToken(level)).append("   ").append(decimal);

//                show = printSpaceToken(level) + "   " + decimal;
//                show =   makeString(printSpaceToken(level ), "   ",decimal );

            } else {

                show.append( printSpaceToken(level-1)).append(leftToken).append("   \"").append(key).append("\" :  ").append(decimal).append(" ");

//                show = printSpaceToken(level - 1) + leftToken + "   \"" + key + "\" :  " + decimal + " ";


//                show =   makeString(printSpaceToken(level - 1),leftToken,"   \"",key,"\" :  ",decimal );

            }

        } else {

            if (TextUtils.isEmpty(key)) {
                if (val == null) {
                    show.append(printSpaceToken(level)).append("   \"\"");
//                    show = printSpaceToken(level) + "   \"\"";
//
//                    show=makeString(printSpaceToken(level), "   \"\"");
                } else {
                    show.append(printSpaceToken(level)).append(val);

//                    show = printSpaceToken(level) + "   " + val;
//                    show=makeString(printSpaceToken(level),"   " ,val.toString());
                }
            } else {

                show.append(printSpaceToken(level-1)).append(leftToken).append("   \"" ).append(key).append("\" :  ").append(val).append(" ");

//                show = printSpaceToken(level - 1) + leftToken + "   \"" + key + "\" :  " + val + " ";

//                show =   makeString(printSpaceToken(level - 1),leftToken,"   \"",key,"\" :  ",val.toString() );
            }

        }
        int index = (level - 1) * tokenCount;
        String sub = show.substring(index, index + 1);
        if (!sub.equals(token)) {
            tempLastChar = sub;
        }
        templevel = level;
        Log.e(TAG, show.toString());
    }

    public String makeString(String... arg) {
        StringBuffer sb = new StringBuffer();
        for (String o : arg) {
            sb.append(o);
        }
        return sb.toString();
    }

}

//验证是否   是否在double 数据时使用了科学计数法显示数据。 貌似不会。