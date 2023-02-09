package io.github.rojae.authsigninweb.common.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebLocationProps {

    @Value("${web.location.index}")
    public String indexWebUrl;

    @Value("${web.location.signin}")
    public String signinWebUrl;

    @Value("${web.location.signup}")
    public String signupWebUrl;

    @Value("${web.location.logout}")
    public String logoutWebUrl;

}
