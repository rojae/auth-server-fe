package io.github.rojae.authsignupweb.common.api;

import io.github.rojae.authsignupweb.common.api.unionApiDto.CheckDuplicateEmail;
import io.github.rojae.authsignupweb.common.api.unionApiDto.CheckDuplicateNickname;
import io.github.rojae.authsignupweb.common.api.unionApiDto.SignupRequest;
import io.github.rojae.authsignupweb.dto.ApiBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "unionApi", url = "${auth.server.be.unionapi.base}")
public interface UnionApi {

    @PostMapping("${auth.server.be.unionapi.check.duplicate.email.url}")
    ApiBase<Object> checkDuplicateEmail(@RequestBody CheckDuplicateEmail request);

    @PostMapping("${auth.server.be.unionapi.check.duplicate.nickname.url}")
    ApiBase<Object> checkDuplicateNickname(@RequestBody CheckDuplicateNickname request);

    @PostMapping("${auth.server.be.unionapi.signup.url}")
    ApiBase<Object> signup(@RequestBody SignupRequest request);

}