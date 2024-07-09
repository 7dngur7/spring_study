package hello.login.web.session;

import hello.login.domain.member.Member;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class SessionManager {
    public static final String SESSION_COOKIE_NAME = "mySessionId";
    Map<String, Object> sessionMap = new HashMap<>();

    public void createSession(Object object, HttpServletResponse response){

        String uuid = UUID.randomUUID().toString();

        sessionMap.put(uuid, object);

        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, uuid);

        response.addCookie(cookie);
    }

    public Object getSession(HttpServletRequest request){
        Cookie cookie = getCookie(request);
        if(cookie==null){
            return null;
        }
        return sessionMap.get(cookie.getValue());
    }

    public void expireSession(HttpServletRequest request){
        Cookie cookie = getCookie(request);

        if(cookie !=null){
            sessionMap.remove(cookie.getValue());
        }
    }

    private static Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }

        return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
                .findAny()
                .orElse(null);
    }
}
