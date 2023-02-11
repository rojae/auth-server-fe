package io.github.rojae.authsignupweb.common.api;

import io.github.rojae.authsignupweb.common.api.smtpApiDto.MailRequestDto;
import io.github.rojae.authsignupweb.common.api.smtpApiDto.MailVerifyRequestDto;
import io.github.rojae.authsignupweb.dto.ApiBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "smtpApi", url = "${smtp.mail.server.base}")
public interface SmtpApi {

    @PostMapping("${smtp.mail.server.send.signupAuthMail.url}")
    ApiBase<Object> sendSignupAuthMail(@RequestBody MailRequestDto request);

    @PostMapping("${smtp.mail.server.verify.signupAuthMail.url}")
    ApiBase<Object> verifySignupAuthMail(@RequestBody MailVerifyRequestDto request);

//    @GetMapping("${smtp.mail.server.send.welcomeMail.url}")
//    ApiBase<Object> clientInfo(@RequestParam(value = "platformType") String platformType);

}
