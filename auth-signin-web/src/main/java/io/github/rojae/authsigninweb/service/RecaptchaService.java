package io.github.rojae.authsigninweb.service;

import io.github.rojae.authsigninweb.common.api.GoogleRecaptchaApi;
import io.github.rojae.authsigninweb.common.api.recaptchaapi.dto.RecaptchaRequest;
import io.github.rojae.authsigninweb.common.props.RecaptchaProps;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecaptchaService {

    private final GoogleRecaptchaApi googleRecaptchaApi;
    private final RecaptchaProps recaptchaProps;

    public boolean doRecaptcha(String token){
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        Map<String, String> request = new HashMap<>();
        request.put("secret", recaptchaProps.secretKey);
        request.put("response", token);
        request.put("remoteip", "");

        Map<String, String> response = googleRecaptchaApi.doRecaptcha(headers, request);
        return Boolean.parseBoolean(response.get("success"));
    }
}
