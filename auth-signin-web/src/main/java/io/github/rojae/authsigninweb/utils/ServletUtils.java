package io.github.rojae.authsigninweb.utils;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public class ServletUtils {

    public static String getHeader(HttpServletRequest request) {
        var headerNames = request.getHeaderNames();
        StringBuilder headers = new StringBuilder();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                headers.append(request.getHeader(headerNames.nextElement()))
                        .append(" | ");
            }
        }

        return headers.toString();
    }

    public static String getQueryString(HttpServletRequest request) {
        return Optional.ofNullable(request.getQueryString()).orElse("");
    }

    public static String getRequestBody(ContentCachingRequestWrapper request) {
        // ContentCachingRequestWrapper를 사용하여, 실제 요청 처리 본문에서 재사용 가능하도록 함
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    return new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    return " - ";
                }
            }
        }
        return " - ";
    }

    public static String getResponseBody(final HttpServletResponse response) throws IOException {
        String payload = null;
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            wrapper.setCharacterEncoding("UTF-8");
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                wrapper.copyBodyToResponse();
            }
        }
        return null == payload ? " - " : payload;
    }

}
