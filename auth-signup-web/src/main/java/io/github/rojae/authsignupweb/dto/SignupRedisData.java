package io.github.rojae.authsignupweb.dto;

import io.github.rojae.authsignupweb.common.enums.PlatformType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRedisData {

    // STEP 0 //
    private String agreePersonalInfo;
    private String agreeAdult;

    // STEP 1 //
    private String email;
    private String platformType;

    // STEP 2 //
    private String password;


    // STEP 3 //
    // 기본정보 입력 (이메일, 패스워드 / 성함, 주민등록번호 앞자리 + 뒷자리 1)
    private String name;
    private String nickname;
    private String identificationNo;
    private String mobileTel;

    // STEP 4 //
    // 마켓팅 선택 약관동의
    private String agreeRecvMail;
    private String agreeRecvSms;

    public SignupRedisData ofSignupEmailVerifyRequest(SignupEmailVerifyRequest request){
        this.setEmail(request.getEmail());
        this.setPlatformType(PlatformType.NONSOCIAL.name());
        return this;
    }

    public SignupRedisData ofSignupPasswordVerifyRequest(SignupPasswordVerifyRequest request){
        this.setPassword(request.getPassword());
        return this;
    }

    public SignupRedisData ofSignupCustomRequest(SignupCustomInfoRequest request){
        this.setName(request.getName());
        this.setNickname(request.getNickname());
        this.setIdentificationNo(request.getIdentificationNo());
        this.setMobileTel(request.getMobileTel());
        return this;
    }

    public SignupRedisData ofSignupOptionTermsRequest(SignupOptionTermsRequest request) {
        this.agreeRecvMail = request.getAgreeRecvMail();
        this.agreeRecvSms = request.getAgreeRecvSms();
        return this;
    }

    // 필수 약관은 저장할때, 셋팅하자 (프론트 페이지에서 체크)
    public SignupRedisData build(){
        agreePersonalInfo = "Y";
        agreeAdult = "Y";
        return this;
    }

}
