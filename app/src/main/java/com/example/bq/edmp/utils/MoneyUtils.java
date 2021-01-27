package com.example.bq.edmp.utils;

import java.text.DecimalFormat;

public class MoneyUtils {

    public static String formatMoney(Double money) {
        if (money != null) {
            return new DecimalFormat("#0.00").format(money);
        }
        return "0.00";
    }

    public static String formatWeight(Double money) {
        if (money != null) {
            return new DecimalFormat("#0.000").format(money);
        }
        return "0.000";
    }
}
