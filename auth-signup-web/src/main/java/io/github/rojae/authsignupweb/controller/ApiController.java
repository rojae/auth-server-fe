package io.github.rojae.authsignupweb.controller;

import io.github.rojae.authsignupweb.common.api.SmtpApi;
import io.github.rojae.authsignupweb.common.api.UnionApi;
import io.github.rojae.authsignupweb.common.api.smtpApiDto.MailRequestDto;
import io.github.rojae.authsignupweb.common.api.smtpApiDto.MailVerifyRequestDto;
import io.github.rojae.authsignupweb.common.api.unionApiDto.CheckDuplicateEmail;
import io.github.rojae.authsignupweb.common.api.unionApiDto.CheckDuplicateNickname;
import io.github.rojae.authsignupweb.common.api.unionApiDto.SignupRequest;
import io.github.rojae.authsignupweb.common.enums.ApiCode;
import io.github.rojae.authsignupweb.common.enums.MailType;
import io.github.rojae.authsignupweb.dto.*;
import io.github.rojae.authsignupweb.service.SignupStepUUIDService;
import io.github.rojae.authsignupweb.utils.BrithDateUtils;
import io.github.rojae.authsignupweb.utils.GenderUtils;
import io.github.rojae.authsignupweb.utils.MobileTelUtils;
import io.github.rojae.authsignupweb.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final UnionApi unionApi;
    private final SmtpApi smtpApi;
    private final SignupStepUUIDService signupStepUUIDService;

    // 회원타입구분 서비스/카카오
    // 약관동의
    // 기본정보 입력 (이메일, 패스워드 / 성함, 주민등록번호 앞자리 + 뒷자리 1)
    // 메일발송 6자리 코드입력 (재발송 기능제공)
    // 최종가입 정보 확인
    // 가입시도 end

    // 프로필 이미지 등록은, 로그인 시 진행

    @PostMapping("/api/v1/mail/send/signupForAuth")
    public ResponseEntity<ApiBase<Object>> sendAuthMail(@RequestBody SignupMailSendRequest requestDto){
        if(ApiCode.ofCode(unionApi.checkDuplicateEmail(new CheckDuplicateEmail(requestDto.getEmail())).getCode()) != ApiCode.OK){
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_DUPLICATE));
        }
        else{
            // 인증메일 전송
            smtpApi.sendSignupAuthMail(new MailRequestDto(requestDto.getEmail(), MailType.SIGNUP.name()));
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SMTP_OK));
        }
    }

    @PostMapping("/api/v1/mail/verify/signupForAuth")
    public ResponseEntity<ApiBase<Object>> mailVerify(@RequestBody @Valid SignupEmailVerifyRequest requestDto, HttpServletRequest request, HttpServletResponse response) {
        // SS_UUID 업데이트가 정상적이고, SMTP 인증에 성공한 경우
        if(!signupStepUUIDService.checkSSUUID(request)){
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_API_INVALID_SSUUID));
        }
        else if(signupStepUUIDService.saveStep1(request, response, requestDto)
                && ApiCode.ofCode(smtpApi.verifySignupAuthMail(new MailVerifyRequestDto(requestDto.getEmail(), requestDto.getSecret())).getCode()) == ApiCode.SMTP_OK) {
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SMTP_OK));
        }
        else
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SMTP_INVALID_SECRET));
    }

    @PostMapping("/api/v1/signup/basic-info/password")
    public ResponseEntity<ApiBase<Object>> passwordVerify(@RequestBody @Valid SignupPasswordVerifyRequest requestDto, HttpServletRequest request, HttpServletResponse response) {
        String passwordCheckResult = PasswordUtils.ruleCheck(requestDto.getEmail(), requestDto.getPassword());

        if(!signupStepUUIDService.checkSSUUID(request)){
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_API_INVALID_SSUUID));
        }
        else if(!StringUtils.isEmpty(passwordCheckResult)){
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_API_NOTALLOW_PASSWORD, passwordCheckResult));
        }
        else if(signupStepUUIDService.saveStep2(request, response, requestDto)){
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_API_OK));
        }
        else {
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_API_BADREQUET_WITHDATA));
        }
    }

    @PostMapping("/api/v1/signup/custom-info/personal")
    public ResponseEntity<ApiBase<Object>> customInfoPersonal(@RequestBody @Valid SignupCustomInfoRequest requestDto, HttpServletRequest request, HttpServletResponse response){
        if(!signupStepUUIDService.checkSSUUID(request)){
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_API_INVALID_SSUUID));
        }
        else if(ApiCode.ofCode(unionApi.checkDuplicateNickname(new CheckDuplicateNickname(requestDto.getNickname())).getCode()) != ApiCode.OK){
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_DUPLICATE));
        }
        else if(signupStepUUIDService.saveStep3(request, response, requestDto)){
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_API_OK));
        }
        else {
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_API_BADREQUET_WITHDATA));
        }
    }

    @PostMapping("/api/v1/signup/option/terms")
    public ResponseEntity<ApiBase<Object>> optionTerms(@RequestBody @Valid SignupOptionTermsRequest requestDto, HttpServletRequest request, HttpServletResponse response){
        if(!signupStepUUIDService.checkSSUUID(request)){
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_API_INVALID_SSUUID));
        }
        else if(signupStepUUIDService.saveStep4(request, response, requestDto)){
            SignupRedisData data = signupStepUUIDService.get(request, response).getData();

            // 가입 진행 :: call union api
            // Welcome 메일 전송은 unionAPI에서 담당
            ApiBase<Object> signupResponse = unionApi.signup(new SignupRequest(
                    data.getName(), data.getEmail(), data.getPassword(),
                    data.getNickname(), data.getPlatformType(), "",
                    BrithDateUtils.getBirthDate(data.getIdentificationNo()),
                    GenderUtils.getGender(data.getIdentificationNo()),
                    MobileTelUtils.mobileTel1(data.getMobileTel()),
                    MobileTelUtils.mobileTel2(data.getMobileTel()),
                    MobileTelUtils.mobileTel3(data.getMobileTel()),
                    data.getAgreePersonalInfo(),
                    data.getAgreeAdult(),
                    data.getAgreeRecvMail(),
                    data.getAgreeRecvSms()
            ));

            if(ApiCode.ofCode(signupResponse.getCode()) == ApiCode.OK){
                return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_API_OK));
            }
            else{
                return ResponseEntity.ok(new ApiBase<>(ApiCode.COREAPI_ERROR));
            }
        }
        else {
            return ResponseEntity.ok(new ApiBase<>(ApiCode.SIGNUP_API_BADREQUET_WITHDATA));
        }
    }


}
