package com.tnasfer.gbict.domain.member.auth.config;

import com.tnasfer.gbict.domain.member.auth.handler.auth.MemberAuthenticationEntryPoint;
import com.tnasfer.gbict.domain.member.auth.jwt.DelegateTokenService;
import com.tnasfer.gbict.domain.member.auth.jwt.JwtTokenizer;
import com.tnasfer.gbict.domain.member.service.MemberRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final DelegateTokenService delegateTokenService;
    private final MemberRoleService roleService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .cors(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .with(customFilterConfigurers(), Customizer.withDefaults())
                .exceptionHandling(conf->
                        conf.authenticationEntryPoint(authenticationEntryPoint()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/auth/**"))
                                .authenticated()
                                .requestMatchers(
//                                        PathRequest.toH2Console(),
                                        AntPathRequestMatcher.antMatcher("/docs/**"),
                                        AntPathRequestMatcher.antMatcher("/public/**")
                                ).permitAll()

                )
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin
                )
                );
        return http.build();
    }
    @Bean
    public CustomFilterConfigurer customFilterConfigurers() {
        return new CustomFilterConfigurer(jwtTokenizer, delegateTokenService, roleService);
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(List.of("http://localhost:8080", "http://localhost:3000", "http://localhost:5173","http://ec2-15-164-224-4.ap-northeast-2.compute.amazonaws.com:8080","http://ec2-15-164-224-4.ap-northeast-2.compute.amazonaws.com:3000","http://ec2-15-164-224-4.ap-northeast-2.compute.amazonaws.com:5173","https://campusbridge.vercel.app"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint(){
        return new MemberAuthenticationEntryPoint();
    }

}
