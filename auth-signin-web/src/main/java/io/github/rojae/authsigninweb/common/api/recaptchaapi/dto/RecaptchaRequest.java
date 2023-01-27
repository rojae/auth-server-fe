package io.github.rojae.authsigninweb.common.api.recaptchaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecaptchaRequest {
    private String secret;
    private String response;
    private String remoteip;
}
