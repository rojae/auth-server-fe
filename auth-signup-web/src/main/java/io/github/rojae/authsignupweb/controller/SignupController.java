package io.github.rojae.authsignupweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupController {

    @GetMapping("/")
    public String index(){
        return "redirect:home";
    }

    @GetMapping("home")
    public String home(){
        return "home";
    }

    @GetMapping("/signup/step1")
    public String step1View(){
        return "signup/step1";
    }

}
