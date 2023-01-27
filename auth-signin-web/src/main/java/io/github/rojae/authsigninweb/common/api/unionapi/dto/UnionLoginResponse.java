package io.github.rojae.authsigninweb.common.api.unionapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnionLoginResponse {
    private String name;
    private String platformType;
    private String email;
    private String profileImage;
    private String token;
}
