package io.github.rojae.authsigninweb.service;

import io.github.rojae.authsigninweb.common.api.UnionApi;
import io.github.rojae.authsigninweb.common.api.unionapi.dto.UnionLoginResponse;
import io.github.rojae.authsigninweb.common.api.unionapi.dto.UnionNonSocialLoginRequest;
import io.github.rojae.authsigninweb.common.enums.ApiCode;
import io.github.rojae.authsigninweb.dto.ApiBase;
import io.github.rojae.authsigninweb.dto.SigninResponse;
import io.github.rojae.authsigninweb.dto.SigninRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NonsocialLoginService {

    private final RecaptchaService recaptchaService;
    private final UnionApi unionApi;

    public ApiBase<SigninResponse> nonsocialLogin(SigninRequest request, boolean isCaptchaUser){
        UnionNonSocialLoginRequest apiRequest = new UnionNonSocialLoginRequest(request.getEmail(), request.getPassword());
        if(isCaptchaUser && !recaptchaService.doRecaptcha(request.getRecaptcha())){
            return new ApiBase<>(ApiCode.CAPTCHA_FAILED, "captcha 인증을 하지 않은 시도입니다.");
        }
        ApiBase<UnionLoginResponse> response = unionApi.nonsocialLogin(apiRequest);
        if(response.getData() == null)
            return new ApiBase<SigninResponse>().setResponse(response.getCode(), response.getReason(), new SigninResponse());
        else
            return new ApiBase<SigninResponse>().setResponse(response.getCode(), response.getReason(), new SigninResponse(response.getData().getName(), response.getData().getPlatformType(), response.getData().getEmail(), response.getData().getProfileImage(), response.getData().getToken()));
    }

}
