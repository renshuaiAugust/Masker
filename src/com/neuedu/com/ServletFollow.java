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
 * Created by ttc on 18-1-9.
 */
@WebServlet("/ServletFollow")
public class ServletFollow extends HttpServlet  // 加关注
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String niname = request.getParameter("name"); //昵称
        Connection conn = null;
        HttpSession hs=request.getSession();
        String masker_user_x2 = (String)hs.getAttribute("Masker_user_x2");

        System.out.println(masker_user_x2);


        try
        {

            conn = DBUtil.connedDB();
            String sql = "select masker_user_x2 from masker_user where masker_user_x7 = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, niname);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                String masker_user_name = rs.getString("masker_user_x2"); //是否关注的人的用户名

                conn = DBUtil.connedDB();
                String sql2 = "select masker_user_x1 from masker_user where masker_user_x2 = ?";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, masker_user_x2);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next())
                {
                    int userID = rs2.getInt("masker_user_x1");

                    conn = DBUtil.connedDB();
                    boolean flag=false;
                    String sql3 = "select masker_Attention_x1 from masker_Attention where masker_Attention_x2 = ?";
                    PreparedStatement ps3 = conn.prepareStatement(sql3);
                    ps3.setString(1, masker_user_name);
                    ResultSet rs3 = ps3.executeQuery();
                    while (rs3.next())
                    {
                        int ID = rs3.getInt("masker_Attention_x1");

                        if(ID==userID)
                        {
                            flag=true;
                        }
                    }
                    if(flag)
                    {
                        String sql4 = "DELETE from masker_Attention where masker_Attention_x2 = ? and masker_Attention_x1=?";
                        PreparedStatement ps4 = conn.prepareStatement(sql4);
                        ps4.setString(1, masker_user_name);
                        ps4.setInt(2,userID);
                        int res=ps4.executeUpdate();
                    }else
                    {
                        String sql4 = "insert  into  masker_Attention VALUES (?,?)";
                        PreparedStatement ps4 = conn.prepareStatement(sql4);
                        ps4.setString(2, masker_user_name);
                        ps4.setInt(1,userID);
                        int res=ps4.executeUpdate();
                    }

                }




            }


        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }


        System.out.println(niname);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
