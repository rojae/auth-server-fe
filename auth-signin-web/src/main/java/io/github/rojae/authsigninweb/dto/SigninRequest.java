package io.github.rojae.authsigninweb.dto;

import io.github.rojae.authsigninweb.common.valid.PlatformTypeValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {
    @PlatformTypeValid
    private String platformType;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String recaptcha;
}
