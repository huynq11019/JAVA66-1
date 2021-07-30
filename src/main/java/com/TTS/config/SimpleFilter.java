package com.TTS.config;

import com.TTS.Util.CookieService;
import com.TTS.sercurity.JWT.TokenPovider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
@Component
@Slf4j
public class SimpleFilter implements Filter {
    @Autowired
    private TokenPovider tokenPovider;
    @Autowired
    private CookieService cookieService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("đang khởi tạo  dữ liệu");
        Filter.super.init(filterConfig);
//        System.out.println("dữ liệu được phép khởi tạo");
    }

    /**
     * The <code>doFilter</code> method of the Filter is called by the container
     * each time a request/response pair is passed through the chain due to a
     * client request for a resource at the end of the chain. The FilterChain
     * passed in to this method allows the Filter to pass on the request and
     * response to the next entity in the chain.
     * <p>
     * A typical implementation of this method would follow the following
     * pattern:- <br>
     * 1. Examine the request<br>
     * 2. Optionally wrap the request object with a custom implementation to
     * filter content or headers for input filtering <br>
     * 3. Optionally wrap the response object with a custom implementation to
     * filter content or headers for output filtering <br>
     * 4. a) <strong>Either</strong> invoke the next entity in the chain using
     * the FilterChain object (<code>chain.doFilter()</code>), <br>
     * 4. b) <strong>or</strong> not pass on the request/response pair to the
     * next entity in the filter chain to block the request processing<br>
     * 5. Directly set headers on the response after invocation of the next
     * entity in the filter chain.
     *
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this
     *                 filter to pass the request and response to for further
     *                 processing
     * @throws IOException      if an I/O error occurs during this filter's
     *                          processing of the request
     * @throws ServletException if the processing fails for any other reason
     */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            log.info("check token " +
                    "");

        chain.doFilter(request,response);
    }

}
