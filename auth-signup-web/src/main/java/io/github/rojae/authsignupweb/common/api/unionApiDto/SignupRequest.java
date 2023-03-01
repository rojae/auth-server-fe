package io.github.rojae.authsignupweb.common.api.unionApiDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    private String email;

    private String password;

    private String name;

    private String platformType;

    private String profileImage;

    private String birthDate;
    private String gender;      // M, F
    private String mobileTel1;
    private String mobileTel2;
    private String mobileTel3;

    private String agreeRecvMail;   // Marketing Mail
}
