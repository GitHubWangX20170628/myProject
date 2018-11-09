package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wangX
 * @Decription:前端过滤器
 * @date 2018/7/17 16:16
 */
//@WebFilter(filterName = "ayUserFilter", urlPatterns = "/*")
public class AyUserFilter implements Filter {
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(

            Arrays.asList("/main/excludefilter", "/login", "/logout", "/register")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------------->>>> init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("------------->>>> doFilter");
        HttpServletRequest request = (HttpServletRequest) req;

        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

        boolean allowedPath = ALLOWED_PATHS.contains(path);
        /*if(allowedPath){*/
            filterChain.doFilter(req, res);
      /*  }else{
            System.out.println("这里是需要处理的url进入的方法");
        }*/

    }

    @Override
    public void destroy() {
        System.out.println("------------->>>> destroy");
    }
}
