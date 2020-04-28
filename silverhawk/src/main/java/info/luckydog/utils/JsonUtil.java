package info.luckydog.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class JsonUtil {

    public static <T> List<T> jsonToList(String objStr, Class<T> c) {
        if (objStr != null && !"".equals(objStr)) {
            try {
                return JSON.parseArray(objStr, c);
            } catch (Exception e) {
                System.out.println("jsonToList-fail, objStr: " + objStr);
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
