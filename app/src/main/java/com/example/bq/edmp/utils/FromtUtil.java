package com.example.bq.edmp.utils;

import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.widget.EditText;

import java.security.MessageDigest;
import java.text.DecimalFormat;

/**
 * MD5加密
 */
public class FromtUtil {
    public final static String getFromt(double d) {
        try {
            DecimalFormat df = new DecimalFormat("#.00");
            String format = df.format(d);
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setEditTextCursorLocation(EditText editText) {
        CharSequence text = editText.getText();
        if (text instanceof Spannable) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }
}