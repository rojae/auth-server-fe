package io.github.rojae.authsignupweb.common.api.smtpApiDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailRequestDto {
    private String email;
    private String mailType;
}
