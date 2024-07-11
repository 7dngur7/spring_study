package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uuid = UUID.randomUUID().toString();

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        try{
            log.info("try uuid:{}", uuid);

            filterChain.doFilter(httpServletRequest, servletResponse);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            log.info("finally uuid:{}", uuid);

        }

    }
}
