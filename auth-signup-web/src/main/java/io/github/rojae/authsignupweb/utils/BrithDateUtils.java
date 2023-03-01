package io.github.rojae.authsignupweb.utils;

public class BrithDateUtils {
    public static String getBirthDate(String identificationNo){
        if(identificationNo.length() < 7)
            return "";
        else
            return "19" + identificationNo.substring(0, 6);     // 900101 -> 19900101
    }
}
