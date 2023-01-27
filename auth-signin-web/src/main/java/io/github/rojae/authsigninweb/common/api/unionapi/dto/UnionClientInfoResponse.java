package io.github.rojae.authsigninweb.common.api.unionapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnionClientInfoResponse {
    private String uri;
    private String clientId;
    private String redirectUri;
    private String responseType;
    private String total;
}