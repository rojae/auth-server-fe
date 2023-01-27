package io.github.rojae.authsigninweb.common.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityProps {

    @Value("${spring.security.ignore-matchers}")
    public String[] IgnoreMatchers;
    public static String tokenName;
    public static String authFailureName;

    @Value("${cookie.token.name}")
    public void setTokenName(String tokenName) {
        SecurityProps.tokenName = tokenName;
    }

    @Value("${cookie.auth-failure.name}")
    public void setAuthFailureName(String authFailureName) {
        SecurityProps.authFailureName = authFailureName;
    }
}

