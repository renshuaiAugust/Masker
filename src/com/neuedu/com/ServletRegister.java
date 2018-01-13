package com.neuedu.com;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
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



/**
 * Created by ttc on 17-12-28.
 */
@WebServlet("/ServletRegister")
public class ServletRegister extends HttpServlet //注册页面后台！
{


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession hs = request.getSession();
        PreparedStatement ps = null;
        String sql = null;
        ResultSet rs = null;

        try
        {
            Connection conn = DBUtil.connedDB();

            String masker_x2 = request.getParameter("name"); //获得页面登陆是邮箱；
            String masker_x3 = request.getParameter("password"); //获得页面登陆是密码；

            sql ="select * from masker_user where masker_user_x2 = ? ";
            ps=conn.prepareStatement(sql);
            ps.setString(1,masker_x2);
            rs = ps.executeQuery();
            if(rs.next())
            {


                response.getWriter().write("注册失败,用户已存在");




            }else
            {

                sql = "insert INTO  masker_user (masker_user_x2,masker_user_x3) VALUES (?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, masker_x2);
                ps.setString(2, masker_x3);
                int r = ps.executeUpdate();
                if (r > 0)
                {
                    hs.setAttribute("Success", "用户注册成功");
                    response.sendRedirect("../page/login2.jsp"); //调整转到登陆页

                }
            }

        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DBUtil.releaseDB(ps);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
