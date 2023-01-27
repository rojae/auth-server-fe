package io.github.rojae.authsigninweb.service;

import io.github.rojae.authsigninweb.common.api.UnionApi;
import io.github.rojae.authsigninweb.common.api.unionapi.dto.UnionClientInfoRequest;
import io.github.rojae.authsigninweb.common.api.unionapi.dto.UnionClientInfoResponse;
import io.github.rojae.authsigninweb.common.api.unionapi.dto.UnionLoginResponse;
import io.github.rojae.authsigninweb.dto.ApiBase;
import io.github.rojae.authsigninweb.dto.ClientInfoRequest;
import io.github.rojae.authsigninweb.dto.ClientInfoResponse;
import io.github.rojae.authsigninweb.dto.SigninResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocialLoginService {

    private final UnionApi unionApi;

    public ApiBase<ClientInfoResponse> clientInfo(String platformType){
        ApiBase<UnionClientInfoResponse> response = unionApi.clientInfo(platformType);
        return new ApiBase<ClientInfoResponse>().setResponse(response.getCode(), response.getReason(), new ClientInfoResponse(response.getData().getUri(), response.getData().getClientId(), response.getData().getRedirectUri(), response.getData().getResponseType(), response.getData().getTotal()));
    }

    public ApiBase<SigninResponse> signinKakao(String code){
        ApiBase<UnionLoginResponse> response = unionApi.kakaoLogin(code);
        if(response == null)
            return null;
        else
            return new ApiBase<SigninResponse>().setResponse(response.getCode(), response.getReason(), new SigninResponse(response.getData().getName(), response.getData().getPlatformType(), response.getData().getEmail(), response.getData().getProfileImage(), response.getData().getToken()));
    }

}
