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
@WebServlet("/ServletLogin")   //��½ҳ��
@MultipartConfig
public class ServletLogin extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession hs = request.getSession();  //����һ��Session����
        PreparedStatement ps = null;
        ResultSet rs = null;


        String Masker_user_x2 = request.getParameter("username"); //���ҳ���½���û�����
        String Masker_user_x3 = request.getParameter("password"); //���ҳ���½�����룻


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
                response.getWriter().write("�û������������");
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
