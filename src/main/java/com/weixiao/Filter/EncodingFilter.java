package com.weixiao.Filter;


import javax.servlet.*;

import java.io.IOException;

/**
 * 解决乱码，全部编码为utf-8之后放行
 *
 * @author weixiao
 * @date 2022/9/7 14:50
 */
public class EncodingFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
