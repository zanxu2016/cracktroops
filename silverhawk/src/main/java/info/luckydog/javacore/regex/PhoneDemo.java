package info.luckydog.javacore.regex;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneDemo {

    @Test
    public void phoneTest() {
        String regex = "^1[3|4|5|6|7|8|9]\\d{9}$";
        String regex2019 = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";

        String phone = "16602630129";

        System.out.println(isMobile(phone, regex));
        System.out.println(isMobile(phone, regex2019));

    }

    public static boolean isMobile(String str, String regex) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        if (StringUtils.isNotBlank(str)) {
            p = Pattern.compile(regex);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }
}
