package io.github.rojae.authsigninweb.utils;

import io.github.rojae.authsigninweb.common.props.SecurityProps;
import org.springframework.ui.Model;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class CaptchaUtils {

    public static final String captchaKeyModel = "captcha";
    private static final int maxAge = 30 * 60;  // 30Min
    private static final int captchaCriteria = 5;


    // captcha 대상 유저인지 검사
    public static boolean isCaptchaUser(HttpServletRequest request){
        if(WebUtils.getCookie(request, SecurityProps.authFailureName) == null)
            return false;
        else
            return WebUtils.getCookie(request, SecurityProps.authFailureName) != null && Integer.parseInt(Objects.requireNonNull(WebUtils.getCookie(request, SecurityProps.authFailureName)).getValue()) > 5;
    }

    // model에 captcha 정보 저장
    public static void captchaSave(HttpServletRequest request, HttpServletResponse response, Model model){
        if(WebUtils.getCookie(request, SecurityProps.authFailureName) == null) {
            CookieUtils.setCookie(SecurityProps.authFailureName, "0", maxAge, "/", response);
            model.addAttribute(captchaKeyModel, false);
        }
        else{
            model.addAttribute(captchaKeyModel, Integer.parseInt(String.valueOf(Objects.requireNonNull(WebUtils.getCookie(request, SecurityProps.authFailureName)).getValue())) > captchaCriteria);
        }
    }
}
