package com.example.bq.edmp.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tl on 2018/5/22.
 */

public class StringUtils {
    /**
     * 判断手机号是否合法
     */
    public static boolean isPhone(String mobiles) {
        // Pattern p =
        // Pattern.compile("^((13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
//
        Pattern p = Pattern.compile("^1(3|5|7|8|4)\\d{9}");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 用于判断用户输入字符长度6到20位
     *
     * @param str
     * @return
     */
    public static boolean isLength(String str) {
        Pattern pattern = Pattern.compile(".{6,20}");
        return pattern.matcher(str).matches();
    }

    public static String intToString(int i) {
        String mMonth = "";
        if (i < 10) {
            switch (i) {
                case 0:
                    mMonth = "00";
                    break;
                case 1:
                    mMonth = "01";
                    break;
                case 2:
                    mMonth = "02";
                    break;
                case 3:
                    mMonth = "03";
                    break;
                case 4:
                    mMonth = "04";
                    break;
                case 5:
                    mMonth = "05";
                    break;
                case 6:
                    mMonth = "06";
                    break;
                case 7:
                    mMonth = "07";
                    break;
                case 8:
                    mMonth = "08";
                    break;
                case 9:
                    mMonth = "09";
                    break;
            }
        } else {
            mMonth = i + "";
        }

        return mMonth;
    }

    public static Boolean getStringIsNull(String string) {

        return string.isEmpty();
    }

    //邮箱验证
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }

    /**
     * 当浮点型数据位数超过10位之后，数据变成科学计数法显示。用此方法可以使其正常显示。
     *
     * @param value
     * @return Sting
     */
    public static String formatFloatNumber(double value) {
        double twoDecimal = getTwoDecimal(value);
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);

        return nf.format(twoDecimal);
    }

    /**
     * 将数据保留两位小数
     */
    public static double getTwoDecimal(double num) {
        DecimalFormat dFormat = new DecimalFormat("#.00");
        String yearString = dFormat.format(num);
        Double temp = Double.valueOf(yearString);
        return temp;
    }

    /**
     * double转String,保留小数点后两位
     *
     * @param num
     * @return
     */
    public static String doubleToString(double num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }




}
