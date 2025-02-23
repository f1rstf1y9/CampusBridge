package com.tnasfer.gbict.global.Authenticate;

import com.tnasfer.gbict.global.error.code.ExceptionCode;
import com.tnasfer.gbict.global.error.exception.BusinessLogicException;
import org.springframework.beans.BeansException;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;



@Component
public class AuthenticationArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthenticationName.class) != null ||
                parameter.getParameterType().equals(String.class) ||
                parameter.getParameterType().equals(Long.class) ||
                parameter.getParameterType().equals(long.class) ||
                parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Class<?> classParam = parameter.getParameterType();
        if(String.class.equals(classParam)){
            return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        }else if (Long.class.equals(classParam) || long.class.equals(classParam) || int.class.equals(classParam) || Integer.class.equals(classParam)){
            return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        }
        throw new BusinessLogicException(ExceptionCode.BAD_TOKEN_DATA);

    }
}