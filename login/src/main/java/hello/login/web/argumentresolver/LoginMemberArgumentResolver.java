package hello.login.web.argumentresolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    // @Login annotaion이 있으면서 MemberType이면 ArgumentResolver가 사용됨
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter 실행");
        // 로그인 annotaion이 붙어있는가
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        // Member Type인가
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());
        return  hasLoginAnnotation && hasMemberType;
    }

    // 컨트롤러 호출 직전에 호출 되어서 필요한 파라미터 정보 생성
    // 세션에 있는 로그인 회원 정보인 member 객체를 찾아서 반환
    // 반환된 member 객체를 파라미터에 전달해준다
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        
        log.info("resolveArgument 실행");

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
