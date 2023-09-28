package com.example.utils;

public class UtilsString {
    public static boolean isEmptyString(String text){
        return text == null || text.trim().length() == 0;
    }
}
