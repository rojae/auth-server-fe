package io.github.rojae.authsignupweb.utils;

public class MobileTelUtils {

    public static String mobileTel1(String mobileTel){
        return mobileTel.substring(0, 3);
    }

    public static String mobileTel2(String mobileTel){
        return mobileTel.substring(3, 7);
    }

    public static String mobileTel3(String mobileTel){
        return mobileTel.substring(7, 11);
    }

}
