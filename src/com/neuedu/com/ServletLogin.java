package com.neuedu.com;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.neuedu.com.DBUtil.releaseDB;


/**
 * Created by ttc on 18-1-3.
 */
@WebServlet("/ServletLogin")   //登陆页面
@MultipartConfig
public class ServletLogin extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession hs = request.getSession();  //创建一个Session对象；
        PreparedStatement ps = null;
        ResultSet rs = null;


        String Masker_user_x2 = request.getParameter("username"); //获得页面登陆是用户名；
        String Masker_user_x3 = request.getParameter("password"); //获得页面登陆是密码；


        try
        {


            Connection conn = DBUtil.connedDB();
            String sql = "select * from  masker_user  where Masker_user_x2= ? AND Masker_user_x3= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, Masker_user_x2);
            ps.setString(2, Masker_user_x3);
            rs = ps.executeQuery();
            if (rs.next())
            {

                hs.setAttribute("Masker_user_x2", Masker_user_x2);
                hs.setAttribute("Masker_user_x3", Masker_user_x3);
                response.getWriter().write("../html/Personal.html");


            } else
            {
                response.getWriter().write("用户名或密码错误");
            }


        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            releaseDB(ps, rs);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
