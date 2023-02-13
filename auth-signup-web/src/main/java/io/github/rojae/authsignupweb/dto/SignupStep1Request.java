package io.github.rojae.authsignupweb.dto;

import io.github.rojae.authsignupweb.common.valid.YNValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupStep1Request {

    @YNValid
    private String email;

}
