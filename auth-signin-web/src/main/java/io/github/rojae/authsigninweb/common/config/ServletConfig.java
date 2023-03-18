package io.github.rojae.authsigninweb.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.rojae.authsigninweb.common.xss.XssProtectFilter;
import io.github.rojae.authsigninweb.common.xss.XssProtectSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@RequiredArgsConstructor
public class ServletConfig {

    private final ObjectMapper mapper;

    // xss filter for request
    @Bean
    public FilterRegistrationBean<XssProtectFilter> secondFilter() {
        FilterRegistrationBean<XssProtectFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssProtectFilter());
        registrationBean.addUrlPatterns("*");
        registrationBean.setOrder(0);
        registrationBean.setName("xss-protect-filter");
        return registrationBean;
    }

    // xss filter for response
    @Bean
    public MappingJackson2HttpMessageConverter characterEscapeConverter() {
        ObjectMapper objectMapper = mapper.copy();
        objectMapper.getFactory().setCharacterEscapes(new XssProtectSupport());
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

}