package io.github.rojae.authsignupweb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class XssRequestController {

    @PostMapping("/xss/request")
    public XssParam xssTest1(@RequestBody XssParam xssParam) {
        return xssParam;
    }

    @PostMapping("/xss/response")
    public XssParam xssTest5(@RequestBody XssParam xssParam) {
        xssParam.setContent("<script>" + xssParam.getContent() + "</script>");
        return xssParam;
    }
}

