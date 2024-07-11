package hello.login.web.argumentResolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginArgumentResolver implements HandlerMethodArgumentResolver, SessionConst {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {

        boolean assignableFrom = Member.class.isAssignableFrom(methodParameter.getParameterType());
        boolean hasParameterAnnotation = methodParameter.hasParameterAnnotation(Login.class);

        return assignableFrom && hasParameterAnnotation;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)nativeWebRequest.getNativeRequest();

        HttpSession httpSession = httpServletRequest.getSession(false);

        if(httpSession == null ){
            return null;
        }

        return httpServletRequest.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
