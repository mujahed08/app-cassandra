package com.cass.twfive.utils;

public class AppUtils {

    public static byte charAtZeroAsPartKey(String str) {
        int partKeyInt = str.charAt(0);
        byte partKey = (byte) partKeyInt;
        return partKey;
    }
}
