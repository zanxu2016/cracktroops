package info.luckydog.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class StringUtil {

    @Test
    public void demoTest() {
        System.out.println(Arrays.toString("姓名|年龄".split("\\|")));
    }

    @Test
    public void replaceContentTest() {
        String content = "【尊敬的#name#，您有一笔账单需要支付。地址：#address#，金额：#amount#元。请于#deadline#之前完成支付。https://tapp.yuxiaor.com/aster/bill_detail?id=#paymentId#】";
        Map<String, Object> replace = new HashMap<>();
        replace.put("name", "eric");
        replace.put("address", "闵行区浦锦街道滨浦新苑七村30号222");
        replace.put("amount", 2000.00);
        replace.put("deadline", "2020/04/30");
        replace.put("paymentId", 390068);
//        content = "无参数字符串";
        System.out.println(replaceContent(content, replace));
    }

    @Test
    public void distinctIpsTest() {
        String ips = "116.62.134.32;47.98.96.93;101.37.152.209;47.98.135.232;47.98.162.85;47.98.202.165;112.124.19.155;47.96.117.220;47.97.207.94;47.98.148.237;47.98.147.99;47.98.214.61;47.98.114.157;47.97.177.242;47.97.158.187;47.96.116.231;101.81.126.221;101.81.126.88;47.96.173.123;101.81.125.190;47.98.148.237;47.98.147.99;47.98.214.61;47.98.114.157;47.97.177.242;47.98.250.128;47.97.158.187;47.96.143.223;47.96.116.231;47.110.135.0";
        System.out.println("before: " + ips);
        ips = distinctIps(ips);
        System.out.println("after: " + ips);
    }

    @Test
    public void stringBufferTest() {
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer.toString().length());

        System.out.println(getAddress1(null, null, null));
    }

    public static String getAddress1(String building, String cell, String room) {
        StringBuffer sv = new StringBuffer();
        String buildingAndCell = getBuildingAndCell(building, cell);
        sv.append(buildingAndCell);
        if (StringUtils.isNotEmpty(room)) {
            sv.append(room);
        } else {
            sv.append("-");
        }
        return sv.toString();
    }
    public static String getBuildingAndCell(String building, String cell) {

        StringBuffer sv = new StringBuffer();
        //集中式 地址无须添加栋坐单元
        if (isNotBlank(building)) {
            sv.append(building + "号");
        }
        if (isNotBlank(cell)) {
            sv.append(cell + "单元");
        }
        return sv.toString();
    }

    public static String distinctIps(String ips) {
        String[] ipArr = ips.split(";");
        Arrays.sort(ipArr);
        Set<String> ipSet = new TreeSet<>(Arrays.asList(ipArr));
        ips = String.join(";", ipSet);
        return ips;
    }

    /**
     * 替换参数
     */
    public static String replaceContent(String content, Map<String, Object> replace) {
        return replaceContentByTag(content, replace, "#");
    }

    /**
     * 替换参数
     */
    public static String replaceContentByTag(String content, Map<String, Object> replace, String tag) {
        Pattern pattern = Pattern.compile(tag + ".*?" + tag);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String keyOriginal = matcher.group();
            String key = matcher.group().replace("#", "");
            String value = String.valueOf(replace.getOrDefault(key, "见详情"));
            content = content.replace(keyOriginal, value);
        }

        return content;
    }
}
