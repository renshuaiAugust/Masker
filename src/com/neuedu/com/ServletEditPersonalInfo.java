package com.neuedu.com;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * Created by ttc on 18-1-9.
 */
@WebServlet("/ServletEditPersonalInfo")
@MultipartConfig
public class ServletEditPersonalInfo extends HttpServlet  //修改个人信息
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Part part = request.getPart("photo");


        String fileName = part.getSubmittedFileName();
        String dbFile=null;
        try
        {
            HttpSession hs = request.getSession();
            String userid = (String) hs.getAttribute("Masker_user_x2");
            Connection conn = null;

            conn = DBUtil.connedDB();

            String sql = "select masker_user_x4 from masker_user where masker_user_x2 = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                dbFile = rs.getString("Masker_user_x4");//头像

            }


        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

     if(!fileName.equals(""))
     {
         String filePath = request.getServletContext().getRealPath("images");
         String fullPath = filePath + File.separator + fileName;
         part.write(fullPath);
         dbFile = "/images/" + fileName;
     }


                  //头像
        String nickname = request.getParameter("nickname");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String userbrief = request.getParameter("userbrief");


        HttpSession hs = request.getSession();
        String Masker_user_x2 = (String) hs.getAttribute("Masker_user_x2"); //获得页面登陆是用户名；

        try
        {

            Connection conn = DBUtil.connedDB();
            String sql = "update masker_user set  Masker_user_x4=?,Masker_user_x5 =?,Masker_user_x6 =?,Masker_user_x7 =?,Masker_user_x9 =? where Masker_user_x2=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dbFile);
            ps.setString(2, gender);
            ps.setString(3, birthday);
            ps.setString(4, nickname);
            ps.setString(5, userbrief);
            ps.setString(6, Masker_user_x2);
            int rs = ps.executeUpdate();
            if (rs > 0)
            {
                response.getWriter().write("修改成功");
            }
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}


