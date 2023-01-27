package io.github.rojae.authsigninweb.common.api.unionapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnionNonSocialLoginRequest {
    private String email;
    private String password;
}