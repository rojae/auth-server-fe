package io.github.rojae.authsigninweb.utils;

import io.github.rojae.authsigninweb.common.props.SecurityProps;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class TokenUtils {

    private static final int maxAge = 2 * 60 * 60;  // 2hour

    public static String getToken(HttpServletRequest request){
        if(WebUtils.getCookie(request, SecurityProps.tokenName) == null){
            // redirect login page
            return "";
        }
        return Objects.requireNonNull(WebUtils.getCookie(request, SecurityProps.tokenName)).getValue();
    }

    public static boolean hasToken(HttpServletRequest request){
        return WebUtils.getCookie(request, SecurityProps.tokenName) != null;
    }

    public static void setToken(String token, HttpServletResponse response){
        CookieUtils.setCookie(SecurityProps.tokenName, token, maxAge, "/", response);
    }

    public static void delToken(HttpServletResponse response){
        CookieUtils.setCookie(SecurityProps.tokenName, null, 0, "/", response);
    }
}
