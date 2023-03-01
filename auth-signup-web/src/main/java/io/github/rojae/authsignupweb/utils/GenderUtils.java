package io.github.rojae.authsignupweb.utils;

public class GenderUtils {

    public static String getGender(String identificationNo){
        if(identificationNo.length() < 7)
            return "";
        else
            return getGender(identificationNo.charAt(6));
    }

    public static String getGender(char ch){
        if(ch == '1' || ch == '3' || ch == '5' || ch == '7')
            return "M";
        else if (ch == '2' || ch == '4' || ch == '6' || ch == '8')
            return "F";
        else
            return "";
    }
}
