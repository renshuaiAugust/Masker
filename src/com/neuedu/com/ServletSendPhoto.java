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
@WebServlet("/ServletSendPhoto")
@MultipartConfig
public class ServletSendPhoto extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Part part = request.getPart("photo");
        System.out.println(part);
        String filename = part.getSubmittedFileName();
        String filepath = request.getServletContext().getRealPath("upload");

        String fullpath = filepath + File.separator + filename;
        part.write(fullpath);


        if (filename != "")
        {
            response.getWriter().write("../upload/" + filename);
        } else
        {

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
                    String touxiang = rs.getString("Masker_user_x4");//Í·Ïñ
                    response.getWriter().write(touxiang);
                }


            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
