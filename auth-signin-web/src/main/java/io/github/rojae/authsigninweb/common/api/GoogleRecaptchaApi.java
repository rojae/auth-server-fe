package io.github.rojae.authsigninweb.common.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "googleRecaptchaApi", url = "${google.recaptcha.v3.api.host}")
public interface GoogleRecaptchaApi {

    @PostMapping("${google.recaptcha.v3.api.url}")
    Map<String, String> doRecaptcha(@RequestHeader Map<String, String> map, @RequestParam Map<String, String> request);

}
