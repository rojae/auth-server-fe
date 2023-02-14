package io.github.rojae.authsignupweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupPasswordVerifyRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
