package io.github.rojae.authsignupweb.controller;

import io.github.rojae.authsignupweb.common.api.SmtpApi;
import io.github.rojae.authsignupweb.common.api.smtpApiDto.MailRequestDto;
import io.github.rojae.authsignupweb.common.api.smtpApiDto.MailVerifyRequestDto;
import io.github.rojae.authsignupweb.common.enums.ApiCode;
import io.github.rojae.authsignupweb.dto.ApiBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final SmtpApi smtpApi;

    // 회원타입구분 서비스/카카오
    // 약관동의
    // 기본정보 입력 (이메일, 패스워드 / 성함, 주민등록번호 앞자리 + 뒷자리 1)
    // 메일발송 6자리 코드입력 (재발송 기능제공)
    // 최종가입 정보 확인
    // 가입시도 end

    // 프로필 이미지 등록은, 로그인 시 진행

    @PostMapping("/api/v1/mail/send/signupForAuth")
    public ResponseEntity<ApiBase<Object>> sendAuthMail(@RequestBody MailRequestDto requestDto){
        smtpApi.sendSignupAuthMail(requestDto);
        return ResponseEntity.ok(new ApiBase<>(ApiCode.STMP_OK));
    }

    @PostMapping("/api/v1/mail/verify/signupForAuth")
    public ResponseEntity<ApiBase<Object>> verify(@RequestBody @Valid MailVerifyRequestDto requestDto){
        if(ApiCode.ofCode(smtpApi.verifySignupAuthMail(requestDto).getCode()) == ApiCode.STMP_OK)
            return ResponseEntity.ok(new ApiBase<>(ApiCode.STMP_OK));
        else
            return ResponseEntity.ok(new ApiBase<>(ApiCode.INVALID_SECRET));
    }

}
