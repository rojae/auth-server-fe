package io.github.rojae.authsignupweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TermViewController {

    @GetMapping("/signup/terms/view/personalInfo")
    public String personalInfo(){
        return "/terms/personalInfo";
    }

}
