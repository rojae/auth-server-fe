package io.github.rojae.authsignupweb.controller;

import io.github.rojae.authsignupweb.common.domain.SignupStepUUID;
import io.github.rojae.authsignupweb.common.props.WebLocationProps;
import io.github.rojae.authsignupweb.service.SignupStepUUIDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SignupController {

    private final WebLocationProps webLocationProps;
    private final SignupStepUUIDService signupStepUUIDService;

    @GetMapping("/")
    public String index(){
        return "redirect:home";
    }

    @GetMapping("home")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model){
        if(signupStepUUIDService.filterRequestRefer(request, webLocationProps.signinWebUrl, webLocationProps.signupWebUrl)){
            model.addAttribute("deny", true);
            return "home";
        }
        signupStepUUIDService.create(request, response);
        return "home";
    }

    @GetMapping("/signup/step1")
    public String step1View(HttpServletRequest request, HttpServletResponse response, Model model){
        SignupStepUUID signupStepUUID = signupStepUUIDService.get(request, response);

        if(signupStepUUID == null){
            model.addAttribute("deny", true);
            return "signup/step1";
        }

        log.debug("SSUUID : {}", String.valueOf(signupStepUUID.getId()));

        return "signup/step1";
    }

}
