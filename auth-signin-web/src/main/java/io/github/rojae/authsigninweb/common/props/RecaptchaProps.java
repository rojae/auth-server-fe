package io.github.rojae.authsigninweb.common.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RecaptchaProps {

    @Value("${google.recaptcha.v3.api.host}")
    public String host;

    @Value("${google.recaptcha.v3.api.url}")
    public String url;

    @Value("${google.recaptcha.v3.api.site-key}")
    public String siteKey;

    @Value("${google.recaptcha.v3.api.secret-key}")
    public String secretKey;

}
