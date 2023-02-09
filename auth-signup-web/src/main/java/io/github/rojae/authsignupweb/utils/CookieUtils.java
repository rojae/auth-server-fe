package io.github.rojae.authsignupweb.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static Cookie setCookie(String cookieName, String cookieValue, int maxAge, String path, HttpServletResponse response){
        Cookie myCookie = new Cookie(cookieName, cookieValue);
        myCookie.setMaxAge(maxAge);
        myCookie.setPath(path); // 모든 경로에서 접근 가능 하도록 설정
        response.addCookie(myCookie);

        return myCookie;
    }
}
