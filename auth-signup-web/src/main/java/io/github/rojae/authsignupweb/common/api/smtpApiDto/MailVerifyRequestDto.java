package io.github.rojae.authsignupweb.common.api.smtpApiDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailVerifyRequestDto {
    private String email;
    private String secret;
}
