package com.example.projectjsf.filter;

import javax.servlet.*;
import java.io.IOException;

public class SessionRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // FIXME: necessary impl
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // FIXME: necessary impl
    }
}

