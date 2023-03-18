package io.github.rojae.authsignupweb.common.xss;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.rojae.authsignupweb.utils.XssUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XssProtectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        XssRequestWrapper wrappedRequest = new XssRequestWrapper((HttpServletRequest) request);
        String body = IOUtils.toString(wrappedRequest.getReader());

        if (!StringUtils.isBlank(body)) {
            Map<String, Object> oldJsonObject = new ObjectMapper().readValue(body, HashMap.class);
            Map<String, Object> newJsonObject = new HashMap<>();
            oldJsonObject.forEach((key, value) -> newJsonObject.put(key, XssUtils.customReplace(XssUtils.charEscape(value.toString()))));
            wrappedRequest.resetInputStream(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(newJsonObject).getBytes());
        }

        chain.doFilter(wrappedRequest, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}