package info.luckydog.utils;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class StringUtils {

    public static String distinctIps(String ips) {
        String[] ipArr = ips.split(";");
        Arrays.sort(ipArr);
        Set<String> ipSet = new TreeSet<>(Arrays.asList(ipArr));
        ips = String.join(";", ipSet);
        return ips;
    }

    public static void main(String[] args) {
        String ips = "116.62.134.32;47.98.96.93;101.37.152.209;47.98.135.232;47.98.162.85;47.98.202.165;112.124.19.155;47.96.117.220;47.97.207.94;47.98.148.237;47.98.147.99;47.98.214.61;47.98.114.157;47.97.177.242;47.97.158.187;47.96.116.231;101.81.126.221;101.81.126.88;47.96.173.123;101.81.125.190;47.98.148.237;47.98.147.99;47.98.214.61;47.98.114.157;47.97.177.242;47.98.250.128;47.97.158.187;47.96.143.223;47.96.116.231;47.110.135.0";
        System.out.println("before: " + ips);
        ips = distinctIps(ips);
        System.out.println("after: " + ips);
    }
}
