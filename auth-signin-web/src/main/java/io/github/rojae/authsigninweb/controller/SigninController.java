package io.github.rojae.authsigninweb.controller;

import io.github.rojae.authsigninweb.common.enums.ApiCode;
import io.github.rojae.authsigninweb.common.enums.PlatformType;
import io.github.rojae.authsigninweb.common.props.SecurityProps;
import io.github.rojae.authsigninweb.dto.*;
import io.github.rojae.authsigninweb.service.NonsocialLoginService;
import io.github.rojae.authsigninweb.service.RecaptchaService;
import io.github.rojae.authsigninweb.service.SocialLoginService;
import io.github.rojae.authsigninweb.utils.CaptchaUtils;
import io.github.rojae.authsigninweb.utils.CookieUtils;
import io.github.rojae.authsigninweb.utils.FailureUtils;
import io.github.rojae.authsigninweb.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SigninController {

    private final RecaptchaService recaptchaService;
    private final NonsocialLoginService nonsocialLoginService;
    private final SocialLoginService socialLoginService;

    @GetMapping("/")
    public String index(){
        return "redirect:signin";
    }

    @GetMapping("/signin")
    public String signinView(Model model, HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        if(TokenUtils.hasToken(servletRequest))
            return "index-web-redirect";
        CaptchaUtils.captchaSave(servletRequest, servletResponse, model);
        log.debug("Session: {}, Login Fail Count: {}", servletRequest.getSession().getId(), Objects.requireNonNull(WebUtils.getCookie(servletRequest, SecurityProps.authFailureName)).getValue());
        return "signin";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response){
        TokenUtils.delToken(response);
        model.addAttribute("isLogout", TokenUtils.hasToken(request));
        return "signin";
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiBase<SigninResponse>> signin(HttpServletRequest servletRequest, HttpServletResponse servletResponse, @RequestBody SigninRequest request){
        if(PlatformType.valueOf(request.getPlatformType()) == PlatformType.NONSOCIAL){
            ApiBase<SigninResponse> loginResponseApiBase = nonsocialLoginService.nonsocialLogin(request, CaptchaUtils.isCaptchaUser(servletRequest));

            if(ApiCode.ofCode(loginResponseApiBase.getCode()) != ApiCode.OK)
                FailureUtils.whenFail(servletRequest, servletResponse);
            else{
                TokenUtils.setToken(loginResponseApiBase.getData().getToken(), servletResponse);
                FailureUtils.whenPass(servletResponse);
            }

            return new ResponseEntity<>(loginResponseApiBase, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ApiBase<>(ApiCode.INVALID_BODY), HttpStatus.OK);
        }
    }

    @GetMapping("/signin/kakao")
    public String signinKakao(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Model model, @RequestParam(value = "code") String code){
        ApiBase<SigninResponse> signinResponseApiBase = socialLoginService.signinKakao(code);

        if(signinResponseApiBase == null || ApiCode.ofCode(signinResponseApiBase.getCode()) != ApiCode.OK){
            FailureUtils.whenFail(servletRequest, servletResponse);
        }
        else{
            FailureUtils.whenPass(servletResponse);
            TokenUtils.setToken(signinResponseApiBase.getData().getToken(), servletResponse);
            model.addAttribute("principal", signinResponseApiBase.getData());
        }
        return "kakao-redirect";              // 여기에서 리디랙션 시키고, 토큰 가져오고, 마이페이지 이동시켜도 될 듯
    }

    @GetMapping("/signin/client-info")
    public ResponseEntity<ApiBase<ClientInfoResponse>> clientInfo(@RequestParam(value = "platformType") String platformType){
        if(PlatformType.valueOfName(platformType) == PlatformType.UNKNOWN)
            return new ResponseEntity<>(new ApiBase<>(ApiCode.INVALID_PLATFORM), HttpStatus.OK);
        else {
            ApiBase<ClientInfoResponse> clientInfoResponseApiBase = socialLoginService.clientInfo(platformType);
            return new ResponseEntity<>(clientInfoResponseApiBase, HttpStatus.OK);
        }
    }

    // For recaptcha Test //
    @PostMapping("/signin/recaptcha")
    @ResponseBody
    public boolean recaptcha(@RequestBody String token){
        return recaptchaService.doRecaptcha(token);
    }

}
