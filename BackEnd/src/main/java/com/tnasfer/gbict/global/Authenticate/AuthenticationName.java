package com.tnasfer.gbict.global.Authenticate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//TODO: 모든 컨트롤러의 사용자 인증 정보를 해당 하는 에너테이션으로 가져오도록 리펙토링해야함
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticationName {
}
