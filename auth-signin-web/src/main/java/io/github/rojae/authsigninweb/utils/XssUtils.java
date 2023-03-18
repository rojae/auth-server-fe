package io.github.rojae.authsigninweb.utils;

import org.apache.commons.text.StringEscapeUtils;

public class XssUtils {
    public static String charEscape(String value) {
        return value == null ? "" : StringEscapeUtils.escapeHtml4(value);
    }

    public static String customReplace(String value){
        return value.replaceAll("script", "");
    }
}
