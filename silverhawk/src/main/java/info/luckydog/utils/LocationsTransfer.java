package info.luckydog.utils;

import java.util.Arrays;

/**
 * LocationsTransfer
 * 高德地图、腾讯地图、谷歌地图
 * 与
 * 百度地图
 * 相互转换
 *
 * @author eric
 * @since 2019/8/23
 */
public class LocationsTransfer {

    /**
     * 中国正常GCJ02坐标---->百度地图BD09坐标
     * 腾讯地图用的也是GCJ02坐标
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return double[] 转换后的经纬度
     */
    public static double[] convertGCJ02ToBD09(double longitude, double latitude) {
        double $x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double $x = longitude;
        double $y = latitude;
        double $z = Math.sqrt($x * $x + $y * $y) + 0.00002 * Math.sin($y * $x_pi);
        double $theta = Math.atan2($y, $x) + 0.000003 * Math.cos($x * $x_pi);
        longitude = $z * Math.cos($theta) + 0.0065;
        latitude = $z * Math.sin($theta) + 0.006;
        return new double[]{longitude, latitude};
    }

    /**
     * 百度地图BD09坐标---->中国正常GCJ02坐标
     * 腾讯地图用的也是GCJ02坐标
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return double[] 转换后的经纬度
     */
    public static double[] convertBD09ToGCJ02(double longitude, double latitude) {
        double $x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double $x = longitude - 0.0065;
        double $y = latitude - 0.006;
        double $z = Math.sqrt($x * $x + $y * $y) - 0.00002 * Math.sin($y * $x_pi);
        double $theta = Math.atan2($y, $x) - 0.000003 * Math.cos($x * $x_pi);
        longitude = $z * Math.cos($theta);
        latitude = $z * Math.sin($theta);
        return new double[]{longitude, latitude};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(convertBD09ToGCJ02(116.85841369628906, 40.38426208496094)));
        System.out.println(Arrays.toString(convertBD09ToGCJ02(0, 0)));
        System.out.println(Arrays.toString(convertBD09ToGCJ02(121.35810852050781250000, 31.15644264221191406250)));
        System.out.println("2019-08-26".compareTo("2019-08-27"));
        System.out.println("s=1&b=2&d=1156575".length());
        System.out.println("2" == null ? null : "2");
        Integer a = null;
//        switch (a) {
//            case 1:
//                System.out.println(1);
//                break;
//        }
    }
}
