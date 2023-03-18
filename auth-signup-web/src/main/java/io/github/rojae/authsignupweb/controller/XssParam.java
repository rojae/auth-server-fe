package io.github.rojae.authsignupweb.controller;

import lombok.Data;

@Data
public class XssParam {
    private String content;
    private String taskName;
}
