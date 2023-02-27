package io.github.rojae.authsignupweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupOptionTermsRequest {

    @NotBlank
    private String agreeRecvMail;       // 이메일 동의여부 (마켓팅)
    @NotBlank
    private String agreeRecvSms;        // SMS 동의여부 (마켓팅)

}
