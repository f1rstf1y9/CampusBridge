package com.tnasfer.gbict.global.config.mdc;


import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ClientIpCheckFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(ClientIpCheckFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            // 클라이언트의 IP 주소를 추출하여 request_id로 설정
            String clientIP = servletRequest.getRemoteAddr();
            log.info("client ip : {} ", clientIP);

            // 다음 필터로 제어 전달, 실제 요청이 로직이 실행되는 지점
            filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
