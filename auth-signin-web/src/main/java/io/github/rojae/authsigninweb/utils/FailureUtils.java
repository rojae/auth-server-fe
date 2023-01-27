package io.github.rojae.authsigninweb.utils;

import io.github.rojae.authsigninweb.common.props.SecurityProps;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class FailureUtils {

    private static final int maxAge = 2 * 60 * 60;  // 2hour

    // 로그인이 성공한 경우, 실패횟수 초기화
    public static void whenPass(HttpServletResponse response){
        CookieUtils.setCookie(SecurityProps.authFailureName, "0", maxAge, "/", response);
    }

    // 로그인이 실패한 경우, 실패횟수 + 1
    public static void whenFail(HttpServletRequest request, HttpServletResponse response){
        if(WebUtils.getCookie(request, SecurityProps.authFailureName) == null){
            CookieUtils.setCookie(SecurityProps.authFailureName, "0", maxAge, "/", response);
        }
        else{
            CookieUtils.setCookie(SecurityProps.authFailureName,
                    String.valueOf(Integer.parseInt(Objects.requireNonNull(WebUtils.getCookie(request, SecurityProps.authFailureName)).getValue()) + 1),
                    maxAge,
                    "/",
                    response);
        }
    }
}
