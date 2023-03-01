package io.github.rojae.authsignupweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupCustomInfoRequest {
    @NotBlank
    private String nickname;                // 성함, 닉네임

    @NotBlank
    private String identificationNo;        // 주민등록번호 앞자리 + 마지막 자리

    @NotBlank
    private String mobileTel;               // 휴대폰번호
}
