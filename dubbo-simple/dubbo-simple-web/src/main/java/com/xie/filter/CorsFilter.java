package com.xie.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
            throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setHeader("Access-Control-Allow-Methods", "*");
        servletResponse.setHeader("Allow", "*");
        servletResponse.setHeader("Access-Control-Allow-Origin", "*");
        servletResponse.setHeader("Content-type", "*");
        servletResponse.setHeader("Access-Control-Allow-Headers", "*");

        arg2.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }

}
