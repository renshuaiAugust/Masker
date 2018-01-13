package com.neuedu.com;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by ttc on 17-12-28.
 */
@WebFilter(filterName = "FilterDemo",urlPatterns = "/*")
public class FilterDemo implements Filter
{
    @Override
    public void destroy()
    {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        //处理中文乱码；
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");

        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException
    {

    }

}
