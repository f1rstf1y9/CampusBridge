package com.tnasfer.gbict.global.config.web;


import com.tnasfer.gbict.global.Authenticate.AuthenticationArgumentResolver;
import com.tnasfer.gbict.global.config.mdc.ClientIpCheckFilter;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationArgumentResolver authenticationArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(authenticationArgumentResolver);
    }
//    @Bean
//    public FilterRegistrationBean addFilter() {
//        // SpringBoot 에서는 FilterRegistrationBean을 이용해서 필터 설정(was 올릴 때 서블릿 컨테이너 올릴 때 알아서 등록을 해준다.)
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new ClientIpCheckFilter()); // 등록할 필터/ 필터 순서
//        filterRegistrationBean.addUrlPatterns("/*"); // 필터 적용할 url 패턴
//
//        return filterRegistrationBean;
//    }
}
