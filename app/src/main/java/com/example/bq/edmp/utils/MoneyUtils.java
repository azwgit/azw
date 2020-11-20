package com.example.bq.edmp.utils;

import java.text.DecimalFormat;

public class MoneyUtils {

    public static String formatMoney(Double money){
        return new DecimalFormat("#0.00").format(money);
    }
}
