package io.github.rojae.authsigninweb.common.api;

import io.github.rojae.authsigninweb.common.api.unionapi.dto.UnionClientInfoRequest;
import io.github.rojae.authsigninweb.common.api.unionapi.dto.UnionClientInfoResponse;
import io.github.rojae.authsigninweb.common.api.unionapi.dto.UnionLoginResponse;
import io.github.rojae.authsigninweb.common.api.unionapi.dto.UnionNonSocialLoginRequest;
import io.github.rojae.authsigninweb.dto.ApiBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "unionApi", url = "${auth.server.be.unionapi}")
public interface UnionApi {

    @PostMapping("${auth.server.be.unionapi.login.nonsocial.url}")
    ApiBase<UnionLoginResponse> nonsocialLogin(@RequestBody UnionNonSocialLoginRequest request);

    @GetMapping("${auth.server.be.unionapi.client-info.url}")
    ApiBase<UnionClientInfoResponse> clientInfo(@RequestParam(value = "platformType") String platformType);

    @GetMapping("${auth.server.be.unionapi.login.kakao.url}")
    ApiBase<UnionLoginResponse> kakaoLogin(@RequestParam(value = "code") String code);
}
