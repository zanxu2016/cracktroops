package info.luckydog.javacore.string;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Test
    public void test() {
        String autherizorInfo = "{\"authorizer_info\":{\"nick_name\":\"不二租体验下吧\",\"head_img\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/Dlal9rxywfzAvvgaOytaibibqqZmdhAt3ppoOrfeOjTntEN81ia00dpU57mOFIQyUFR7ZTFMYF5Gs8Utv4bBmWibY10BGAyfTL0F\\/0\",\"service_type_info\":{\"id\":2},\"verify_type_info\":{\"id\":0},\"user_name\":\"gh_a50ae414df29\",\"alias\":\"\",\"qrcode_url\":\"http:\\/\\/mmbiz.qpic.cn\\/mmbiz_jpg\\/WtYhkhVQ0fbNzx9SDuWqwPKHGwGaibW9dRnDFqZApuNIefNWVGa9oNTKSpQiazH129t98iaHEy2GYbsKXT68ntErA\\/0\",\"business_info\":{\"open_pay\":0,\"open_shake\":0,\"open_scan\":0,\"open_card\":0,\"open_store\":0},\"idc\":1,\"principal_name\":\"上海和住信息科技有限公司\",\"signature\":\"不二租开发使用的公众号\"},\"authorization_info\":{\"authorizer_appid\":\"wxd459be97b90f047b\",\"authorizer_refresh_token\":\"refreshtoken@@@0i1ekZ-hzkAmKdXLkpgIm3uFQsL2r9-8xK_RF56AmUE\",\"func_info\":[{\"funcscope_category\":{\"id\":1}},{\"funcscope_category\":{\"id\":15}},{\"funcscope_category\":{\"id\":4}},{\"funcscope_category\":{\"id\":7}},{\"funcscope_category\":{\"id\":2}},{\"funcscope_category\":{\"id\":3}},{\"funcscope_category\":{\"id\":11}},{\"funcscope_category\":{\"id\":6}},{\"funcscope_category\":{\"id\":5}},{\"funcscope_category\":{\"id\":8}},{\"funcscope_category\":{\"id\":13}},{\"funcscope_category\":{\"id\":9}},{\"funcscope_category\":{\"id\":10}},{\"funcscope_category\":{\"id\":12}},{\"funcscope_category\":{\"id\":22}},{\"funcscope_category\":{\"id\":23}},{\"funcscope_category\":{\"id\":24},\"confirm_info\":{\"need_confirm\":0,\"already_confirm\":0,\"can_confirm\":0}},{\"funcscope_category\":{\"id\":26}},{\"funcscope_category\":{\"id\":27},\"confirm_info\":{\"need_confirm\":0,\"already_confirm\":0,\"can_confirm\":0}},{\"funcscope_category\":{\"id\":33},\"confirm_info\":{\"need_confirm\":0,\"already_confirm\":0,\"can_confirm\":0}},{\"funcscope_category\":{\"id\":34}},{\"funcscope_category\":{\"id\":35}},{\"funcscope_category\":{\"id\":44},\"confirm_info\":{\"need_confirm\":0,\"already_confirm\":0,\"can_confirm\":0}},{\"funcscope_category\":{\"id\":46}}]}}";

        System.out.println(autherizorInfo.length());// 1814


        String menuStr = "{\"is_menu_open\":1,\"selfmenu_info\":{\"button\":[{\"type\":\"view\",\"name\":\"我的合同\",\"url\":\"http:\\/\\/www-g2.yuxiaor.com\\/c-1781\\/tapp-page?tappPage=4\"},{\"name\":\"交房租\",\"sub_button\":{\"list\":[{\"type\":\"view\",\"name\":\"我要找房\",\"url\":\"http:\\/\\/www-g2.yuxiaor.com\\/c-1781\\/tapp-page?tappPage=1\"}]}},{\"name\":\"个人中心\",\"sub_button\":{\"list\":[{\"type\":\"view\",\"name\":\"我的合同\",\"url\":\"https:\\/\\/www.yuxiaor.com\\/c-1781\\/contracts\"},{\"type\":\"view\",\"name\":\"远程开门\",\"url\":\"https:\\/\\/www.yuxiaor.com\\/c-1781\\/house\\/unlock-door\"},{\"type\":\"view\",\"name\":\"我的账号\",\"url\":\"https:\\/\\/www.yuxiaor.com\\/c-1781\\/profile\\/my\"}]}}]}}";

//        WoaMenuRes menuRes = JSON.parseObject(menuStr, WoaMenuRes.class);

        WoaMenuRes menuRes = jsonToObject(menuStr, WoaMenuRes.class);

        System.out.println(menuRes.getIs_menu_open());

    }

    @Test
    public void testJSON() {
        String funcInfo = "[{\"funcscope_category\":{\"id\":1}},{\"funcscope_category\":{\"id\":15}},{\"funcscope_category\":{\"id\":4}},{\"funcscope_category\":{\"id\":7}},{\"funcscope_category\":{\"id\":2}},{\"funcscope_category\":{\"id\":3}},{\"funcscope_category\":{\"id\":11}},{\"funcscope_category\":{\"id\":6}},{\"funcscope_category\":{\"id\":5}},{\"funcscope_category\":{\"id\":8}},{\"funcscope_category\":{\"id\":13}},{\"funcscope_category\":{\"id\":9}},{\"funcscope_category\":{\"id\":10}},{\"funcscope_category\":{\"id\":12}},{\"funcscope_category\":{\"id\":22}},{\"funcscope_category\":{\"id\":23}},{\"funcscope_category\":{\"id\":24},\"confirm_info\":{\"need_confirm\":0,\"already_confirm\":0,\"can_confirm\":0}},{\"funcscope_category\":{\"id\":26}},{\"funcscope_category\":{\"id\":27},\"confirm_info\":{\"need_confirm\":0,\"already_confirm\":0,\"can_confirm\":0}},{\"funcscope_category\":{\"id\":33},\"confirm_info\":{\"need_confirm\":0,\"already_confirm\":0,\"can_confirm\":0}},{\"funcscope_category\":{\"id\":34}},{\"funcscope_category\":{\"id\":35}},{\"funcscope_category\":{\"id\":44},\"confirm_info\":{\"need_confirm\":0,\"already_confirm\":0,\"can_confirm\":0}},{\"funcscope_category\":{\"id\":46}}]";

        Optional<Integer> customMenu = JSON.parseArray(funcInfo).toJavaList(JSONObject.class)
                .stream()
                .map(jsonObject -> jsonObject.getJSONObject("funcscope_category").getIntValue("id"))
                .filter(integer -> integer == 15).findFirst();
        System.out.println(customMenu.isPresent());

        List<String> funcList = JSON.parseArray(funcInfo).toJavaList(JSONObject.class)
                .stream()
                .map(jsonObject -> jsonObject.getJSONObject("funcscope_category").getIntValue("id"))
                .map(WechatFuncEnum::getDescByType)
                .collect(Collectors.toList());
        System.out.println(funcList);
    }

    public static <T> T jsonToObject(String objStr, Class<T> c) {
        if (objStr != null && !"".equals(objStr)) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(objStr, c);
            } catch (Exception var3) {
                System.out.println("jsonToObject-fail-1, objStr: " + objStr);
                var3.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @Test
    public void testCapitalize() {
        String text = "event";
        System.out.println(StringUtils.capitalize(text));
    }

    @Test
    public void encoding() throws UnsupportedEncodingException {
        String content = "测试";
        String name = "*赞";

        String gbkContent = new String(content.getBytes(Charset.forName("UTF8")), "GBK");
        String gbkName = new String(name.getBytes(Charset.forName("UTF8")), "GBK");
        System.out.println(gbkContent);
        System.out.println(gbkName);

        String utf8Content = new String(gbkContent.getBytes(Charset.forName("GBK")), "UTF8");
        String utf8Name = new String(gbkName.getBytes(Charset.forName("GBK")), "UTF8");
        System.out.println(utf8Content);
        System.out.println(utf8Name);

        System.out.println(Arrays.toString(name.getBytes("UTF8")));
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

@Data
class WoaMenuRes {
    private int is_menu_open;// 是否开通自定义菜单，0-未开通，1-已开通
    private String selfmenu_info;// 自定义菜单json字符串
//    private SelfMenuInfo selfmenu_info;// 自定义菜单实体

    @Data
    class SelfMenuInfo {
        private List<Button> button;
    }

    @Data
    class Button {
        private String type;// 菜单类型，view/click/miniprogram
        private String name;// 菜单名称
        private String url;// view类型菜单的链接
        private SubButton sub_button;// 下级菜单
        private String appid;// miniprogram类型菜单的小程序appid
        private String pagepath;// miniprogram类型菜单的小程序页面路径
    }

    @Data
    class SubButton {
        private List<Button> list;
    }
}


enum WechatFuncEnum {

    MSG_MANAGEMENT(1, "消息管理权限"),
    USER_MANAGEMENT(2, "用户管理权限"),
    ACCOUNT_SERVICE(3, "帐号服务权限"),
    WEB_SERVICE(4, "网页服务权限"),
    WECHAT_STORE(5, "微信小店权限"),
    WECHAT_MULTI_SERVICE(6, "微信多客服权限"),
    MASS_NOTIFY(7, "群发与通知权限"),
    WECHAT_CARD(8, "微信卡券权限"),
    WECHAT_SCAN(9, "微信扫一扫权限"),
    WECHAT_WIFI(10, "微信连WIFI权限"),
    MATERIAL_MANAGEMENT(11, "素材管理权限"),
    WECHAT_SHAKE_AROUND(12, "微信摇周边权限"),
    WECHAT_SHOP(13, "微信门店权限"),
    CUSTOM_MENU(15, "自定义菜单权限"),
    GET_AUTH_INFO(16, "获取认证状态及信息"),
    ACCOUNT_MANAGEMENT_WXA(17, "帐号管理权限"),
    DEV_MANAGEMENT_DATA_ANALYSIS_WXA(18, "开发管理与数据分析权限"),
    SERVICE_MSG_MANAGEMENT_WXA(19, "客服消息管理权限"),
    WECHAT_LOGIN_WXA(20, "微信登录权限"),
    DATA_ANALYSIS_WXA(21, "数据分析权限"),
    CITY_SERVICE(22, "城市服务接口权限"),
    AD_MANAGEMENT(23, "广告管理权限"),
    OPEN_PLATFORM_ACCOUNT_MANAGEMENT(24, "开放平台帐号管理权限"),
    OPEN_PLATFORM_ACCOUNT_MANAGEMENT_WXA(25, "开放平台帐号管理权限"),
    WECHAT_E_INVOICE(26, "微信电子发票权限"),
    FAST_REGISTER(27, "快速注册小程序权限"),
    WXA_MANAGEMENT(33, "小程序管理权限"),
    SEARCH_WIDGET(41, "搜索widget的权限"),
    ONE_THING_ONE_CODE(46, "微信一物一码权限"),
    UNKNOWN(999, "未知权限"),
    ;

    @Getter
    @Setter
    private int type;

    @Getter
    @Setter
    private String desc;


    WechatFuncEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByType(int type) {
        return Arrays.stream(WechatFuncEnum.values())
                .filter(wechatFuncEnum -> type == wechatFuncEnum.getType())
                .findFirst()
                .orElse(WechatFuncEnum.UNKNOWN)
                .getDesc();
    }
}
