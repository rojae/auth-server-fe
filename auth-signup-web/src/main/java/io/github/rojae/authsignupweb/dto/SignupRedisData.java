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
    // 약관동의
    private String term1;
    private String term2;
    private String term3;

    // STEP 3 //
    // 기본정보 입력 (이메일, 패스워드 / 성함, 주민등록번호 앞자리 + 뒷자리 1)
    private String email;
    private String password;
    private String name;
    private String identificationNo;

    // STEP 4 //
    private String mailAuthCode;

    public SignupRedisData ofSignupEmailVerifyRequest(SignupEmailVerifyRequest request){
        this.setEmail(request.getEmail());
        return this;
    }

    public SignupRedisData ofSignupPasswordVerifyRequest(SignupPasswordVerifyRequest request){
        this.setEmail(request.getEmail());
        this.setPassword(request.getPassword());
        return this;
    }

}
