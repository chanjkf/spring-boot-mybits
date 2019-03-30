package top.chanjkf.test.util;

import com.alibaba.druid.support.json.JSONUtils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static String success(Object obj) {
        Map<String, Object> map = new HashMap();
        map.put("code", 0);
        map.put("msg", "success");
        map.put("data", obj);
        return JSONUtils.toJSONString(map);
    }

    public static String fail(String msg, Object obj) {
        Map<String, Object> map = new HashMap();
        map.put("code", -1);
        map.put("msg", msg);
        map.put("data", obj);
        return JSONUtils.toJSONString(map);
    }
}
