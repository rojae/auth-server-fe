package io.github.rojae.authsignupweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupCustomInfoRequest {
    private String nickName;                // 성함, 닉네임
    private String identificationNo;        // 주민등록번호 앞자리 + 마지막 자리
    private String mobileTel1;              // 휴대폰번호 1
    private String mobileTel2;              // 휴대폰번호 2
    private String mobileTel3;              // 휴대폰번호 3
}
