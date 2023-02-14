package io.github.rojae.authsignupweb.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtils {

    public static String ruleCheck(String email, String pwd){
        // 15자 미만
        if(pwd.length() < 8 || pwd.length() > 15)
            return "비밀번호는 8자 이상 15자 이하만 가능합니다";

        // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        Matcher passMatcher1 = passPattern1.matcher(pwd);

        if(!passMatcher1.find()){
            return "비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다";
        }

        // 반복된 문자 확인
        Pattern passPattern2 = Pattern.compile("(\\w)\\1\\1\\1");
        Matcher passMatcher2 = passPattern2.matcher(pwd);

        if(passMatcher2.find()){
            return "비밀번호에 동일한 문자를 과도하게 연속해서 사용할 수 없습니다";
        }

        // 아이디 포함 확인
        if(pwd.contains(email)){
            return "비밀번호에 이메일을 포함할 수 없습니다";
        }

        // 특수문자 확인
        Pattern passPattern3 = Pattern.compile("\\W");
        Pattern passPattern4 = Pattern.compile("[!@#$%^*+=-]");

        for(int i = 0; i < pwd.length(); i++){
            String s = String.valueOf(pwd.charAt(i));
            Matcher passMatcher3 = passPattern3.matcher(s);

            if(passMatcher3.find()){
                Matcher passMatcher4 = passPattern4.matcher(s);
                if(!passMatcher4.find()){
                    return "비밀번호에 특수문자는 !@#$^*+=-만 사용 가능합니다";
                }
            }
        }

        return "";
    }

}
