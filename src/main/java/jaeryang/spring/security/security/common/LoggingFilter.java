package jaeryang.spring.security.security.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;


public class LoggingFilter extends GenericFilterBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //성능 측정
        StopWatch stopWatch = new StopWatch();
        stopWatch.start((((HttpServletRequest) servletRequest).getRequestURI()));

        filterChain.doFilter(servletRequest, servletResponse);

        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());

    }
}
