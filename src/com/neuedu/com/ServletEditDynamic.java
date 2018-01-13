package com.neuedu.com;

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
 * Created by ttc on 18-1-8.
 */
@WebServlet("/ServletEditDynamic")
public class ServletEditDynamic extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession hs = request.getSession();
        String username = (String) hs.getAttribute("Masker_user_x2");



        try
        {

            Connection conn =DBUtil.connedDB();
            String sql1 = "select masker_user_x4 from masker_user where masker_user_x2 = ? ";
            PreparedStatement ps1 = null;

            ps1 = conn.prepareStatement(sql1);

            ps1.setString(1, username);
            ResultSet rs = ps1.executeQuery();
            if (rs.next())
            {
                String topic_images = rs.getString("masker_user_x4");

                String json = "{\"thisimg\":\"" + topic_images + "\",\"thisname\":\"" + username+"\"}";




                response.getWriter().write(json);
            }



        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
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
