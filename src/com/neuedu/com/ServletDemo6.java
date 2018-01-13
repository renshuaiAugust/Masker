package com.neuedu.com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

/**
 * Created by ttc on 17-12-28.
 */
@WebServlet("/ServletDemo6") //请求ServletDemo6返回一个4位随机数，字符创格式；JXAX;
public class ServletDemo6 extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Random rand = new Random();
        int a = rand.nextInt(100)*47+3068;
        String sa = Integer.toString(a);
        response.getWriter().write(sa);


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
