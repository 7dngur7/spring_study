package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter, SessionConst {

    private static final String[] whiteList={"/", "/members/add", "/login", "/logout","/css/*"};
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestURI = httpServletRequest.getRequestURI();

        try{
            log.info("인증 체크 필터 시작 {}", requestURI);

            if(!isWhiteList(requestURI)){
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpServletRequest.getSession(false);

                if(session==null || session.getAttribute(LOGIN_MEMBER)==null){
                    httpServletResponse.sendRedirect("/login?redirectURL="+requestURI);
                    return;
                }

            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
        catch (Exception e){

        }
        finally {
            log.info("인증 체크 필터 종료 {}", requestURI);

        }
    }

    public boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
