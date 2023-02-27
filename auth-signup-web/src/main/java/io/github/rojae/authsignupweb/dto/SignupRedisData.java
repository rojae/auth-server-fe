package io.github.rojae.authsignupweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRedisData {

    // STEP 1 //
    // 회원타입구분 서비스/카카오
    private String platformType;

    // STEP 2 //
    // 필수 약관동의
    private String agreePersonalInfo;
    private String agreeAdult;

    // STEP 3 //
    // 기본정보 입력 (이메일, 패스워드 / 성함, 주민등록번호 앞자리 + 뒷자리 1)
    private String email;
    private String password;
    private String name;
    private String identificationNo;
    private String mobileTel;

    // STEP 4 //
    // 마켓팅 선택 약관동의
    private String agreeRecvMail;
    private String agreeRecvSms;

    public SignupRedisData ofSignupEmailVerifyRequest(SignupEmailVerifyRequest request){
        this.setEmail(request.getEmail());
        return this;
    }

    public SignupRedisData ofSignupPasswordVerifyRequest(SignupPasswordVerifyRequest request){
        this.setEmail(request.getEmail());
        this.setPassword(request.getPassword());
        return this;
    }

    public SignupRedisData ofSignupCustomRequest(SignupCustomInfoRequest request){
        this.setMobileTel(request.getMobileTel());
        this.setIdentificationNo(request.getIdentificationNo());
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
