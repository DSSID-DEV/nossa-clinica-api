package com.nossaclinica_api.config.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomCorsInterceptor implements HandlerInterceptor {
    private String[] origins;

    public CustomCorsInterceptor(String[] origins) {
        this.origins = origins;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String origin = request.getHeader(HttpHeaders.ORIGIN);
        Set<String> allowedOrigins = Arrays.stream(origins).collect(Collectors.toSet());
        if(StringUtils.isNotBlank(origin) && !allowedOrigins.contains(origin)){
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }
        return true;

    }
}
