package info.luckydog.javacore.string;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * String2Json
 *
 * @author eric
 * @since 2019/12/17
 */
public class String2Json {

    public static void main(String[] args) {
        int offset = 0;
        int maxCount = 500;
        String appId = "wxb657af5cc6d5157e1";
        ThirdPartAuthorizerRes res = getThirdPartAuthorizerRes(appId, offset, maxCount);
        System.out.println(JSON.toJSONString(res));
    }

    public static ThirdPartAuthorizerRes getThirdPartAuthorizerRes(String appId, int offset, int maxCount) {
        String jsonArrayStr = "{\n" +
                "    \"total_count\": 500,\n" +
                "    \"list\": [\n" +
                "        {\n" +
                "            \"authorizer_appid\": \"wxd459be97b90f047b\",\n" +
                "            \"refresh_token\": \"refreshtoken@@@1Wn_VjhVROq_4vtprytqnWZehpcopFQewkN9yeSl1rc\",\n" +
                "            \"auth_time\": 1573802338\n" +
                "        },\n" +
                "        {\n" +
                "            \"authorizer_appid\": \"wxae1b5ae08875fc25\",\n" +
                "            \"refresh_token\": \"refreshtoken@@@P5nKc4YGZAxtSzIQyqee-1sT3_LKYH9j3lujAQRacTM\",\n" +
                "            \"auth_time\": 1557225350\n" +
                "        },\n" +
                "        {\n" +
                "            \"authorizer_appid\": \"wx1099d2c9ae02bc24\",\n" +
                "            \"refresh_token\": \"refreshtoken@@@5GRidTv0AChHHtja7DeNeFZI3GPEIZ1sK4EmXH0D_p0\",\n" +
                "            \"auth_time\": 1556419142\n" +
                "        },\n" +
                "        {\n" +
                "            \"authorizer_appid\": \"wxc4ad570548789ab7\",\n" +
                "            \"refresh_token\": \"refreshtoken@@@qnhlnn3_WKBUW8QJ40pBSac5_sVk49rL92xYcG8a140\",\n" +
                "            \"auth_time\": 1556415260\n" +
                "        },\n" +
                "        {\n" +
                "            \"authorizer_appid\": \"wxb657af5cc6d5157e\",\n" +
                "            \"refresh_token\": \"refreshtoken@@@5YQRvnofrOc1Ks5ExwRmjfv2XsAyBIWEbi-pANy91jE\",\n" +
                "            \"auth_time\": 1560418163\n" +
                "        },\n" +
                "        {\n" +
                "            \"authorizer_appid\": \"wx376db3e7e6e6a818\",\n" +
                "            \"refresh_token\": \"refreshtoken@@@vcxbRCO47IOnmHiWhXcyMiaT47XvcPwglv9LzFZ01R0\",\n" +
                "            \"auth_time\": 1554365351\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        JSONObject resJSON = JSON.parseObject(jsonArrayStr);
        jsonArrayStr = resJSON.getString("list");// 授权公众号/小程序集合
        List<ThirdPartAuthorizerRes> resList = JSON.parseArray(jsonArrayStr, ThirdPartAuthorizerRes.class);
        Optional<ThirdPartAuthorizerRes> resOpt = resList.parallelStream().filter(item -> appId.equals(item.getAuthorizer_appid())).findFirst();
        if (resOpt.isPresent()) {
            System.out.println("Got it! Then return");
            return resOpt.get();
        }
        boolean left = false;
        if (resList.size() == maxCount) {
            System.out.println("微信那边还有没获取到的item");
            left = true;
        }
        if (continueSeek(resOpt, left)) {
            System.out.println("继续去微信拿...");
            return getThirdPartAuthorizerRes(appId, offset + maxCount, maxCount);
        }
        System.out.println("Find nothing!");
        return null;
    }

    public static boolean continueSeek(Optional resOpt, boolean left) {
        return !resOpt.isPresent() && left;
    }
}

@Data
class ThirdPartAuthorizerRes {
    // 授权者appid
    private String authorizer_appid;
    // 刷新token
    private String refresh_token;
    // 授权时间戳
    private long auth_time;
}
