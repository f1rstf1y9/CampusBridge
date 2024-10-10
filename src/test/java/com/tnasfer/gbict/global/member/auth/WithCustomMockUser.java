package com.tnasfer.gbict.global.member.auth;


import org.springframework.security.test.context.support.WithSecurityContext;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomMockUserSecurityContextFactory.class)
public @interface WithCustomMockUser {
    String id() default "testId";
}
