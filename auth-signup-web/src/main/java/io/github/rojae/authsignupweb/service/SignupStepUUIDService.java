package io.github.rojae.authsignupweb.service;

import io.github.rojae.authsignupweb.common.domain.SignupStepUUID;
import io.github.rojae.authsignupweb.common.props.WebLocationProps;
import io.github.rojae.authsignupweb.dto.SignupRedisData;
import io.github.rojae.authsignupweb.dto.SignupStep1Request;
import io.github.rojae.authsignupweb.repository.SignupStepUUIDRepository;
import io.github.rojae.authsignupweb.utils.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignupStepUUIDService {

    private final SignupStepUUIDRepository signupStepUUIDRepository;
    private final WebLocationProps webLocationProps;
    private static final String SSUUID_NAME = "signup_step_uuid";
    private static final int SSUID_VALUE_LEN = 64;
    private static final int maxAge = 30 * 60;  // 30Min
    private static final String path = "/";

    public boolean create(HttpServletRequest request, HttpServletResponse response){
        // Create Random Text (64)
        String ssuuidValue = RandomStringUtils.randomAlphabetic(SSUID_VALUE_LEN);

        // Browser save cookie
        Cookie ssuuidCookie = CookieUtils.setCookie(SSUUID_NAME, ssuuidValue, maxAge, path, response);

        // Save to Redis
        signupStepUUIDRepository.save(new SignupStepUUID(ssuuidCookie, new SignupRedisData()));

        return true;
    }

    public SignupStepUUID get(HttpServletRequest request, HttpServletResponse response){
        if(Arrays.stream(request.getCookies()).noneMatch(s -> s.getName().equals(SSUUID_NAME))){
            return null;
        }
        else{
            Cookie cookie = WebUtils.getCookie(request, SSUUID_NAME);
            Optional<SignupStepUUID> selectedSSUUID = Optional.empty();
            if (cookie != null) {
                selectedSSUUID = signupStepUUIDRepository.findById(SignupStepUUID.idFormat(cookie.getName(), cookie.getValue()));
            }
            return selectedSSUUID.orElse(null);
        }
    }

    public SignupRedisData getData(HttpServletRequest request, HttpServletResponse response){
        if(Arrays.stream(request.getCookies()).noneMatch(s -> s.getName().equals(SSUUID_NAME))){
            return null;
        }
        else{
            Cookie cookie = WebUtils.getCookie(request, SSUUID_NAME);
            Optional<SignupStepUUID> selectedSSUUID = Optional.empty();
            System.out.println(cookie);
            if (cookie != null) {
                selectedSSUUID = signupStepUUIDRepository.findById(SignupStepUUID.idFormat(cookie.getName(), cookie.getValue()));
            }
            return selectedSSUUID.map(SignupStepUUID::getData).orElse(null);
        }
    }

    public boolean filterRequestRefer(HttpServletRequest request, String... enablePathArray) {
        String requestPath = request.getHeader("referer");

        for(String enablePath : enablePathArray){
            if (requestPath == null || requestPath.contains(enablePath))
                return false;
        }

        return true;
    }


    ///////////// STEP 1 ////////////////
    @Transactional(readOnly = false)
    public boolean saveStep1(HttpServletRequest request, HttpServletResponse response, SignupStep1Request step1Request){
        String val = CookieUtils.getCookie(request, SSUUID_NAME);
        log.debug("step1's header : {}", val);

        if(!StringUtils.isEmpty(val)){
            Optional<SignupStepUUID> byId = signupStepUUIDRepository.findById(SignupStepUUID.idFormat(SSUUID_NAME, val));
            if(byId.isPresent()){
                SignupStepUUID ssUUID = new SignupStepUUID(SignupStepUUID.idFormat(SSUUID_NAME, val), new SignupRedisData().ofStep1(step1Request));
                signupStepUUIDRepository.save(ssUUID);      // update step1's result in redis server
                return true;
            }
            return false;
        }
        return false;
    }
}
