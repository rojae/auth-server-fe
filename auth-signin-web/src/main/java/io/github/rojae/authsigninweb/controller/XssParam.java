package io.github.rojae.authsigninweb.controller;

import lombok.Data;

@Data
public class XssParam {
    private String content;
    private String taskName;
}
