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
 * Created by ttc on 18-1-9.
 */
@WebServlet("/ServletPersonalInfo")  //显示本人信息；
public class ServletPersonalInfo extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession hs = request.getSession();
        String userid = (String) hs.getAttribute("Masker_user_x2");
        Masker_user u = getUserFromDB(userid);




        String UJson = JSON.toJSONString(u);
        System.out.println(UJson+"kkk");
        response.getWriter().write(UJson);



//        hs.setAttribute("masker_user", u);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    private Masker_user getUserFromDB(String userid)
    {
        Masker_user mu = new Masker_user();

        try
        {
            Connection conn = null;

            conn = DBUtil.connedDB();

            String sql = "select * from masker_user where masker_user_x2 = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                mu.setMasker_user_x2(rs.getString("Masker_user_x7"));//昵称
                mu.setMasker_user_x4(rs.getString("Masker_user_x4"));//头像
                mu.setMasker_user_x5(rs.getString("Masker_user_x5"));//性别
                mu.setMasker_user_x6(rs.getString("Masker_user_x6"));//出生年月
                mu.setMasker_user_x9(rs.getString("Masker_user_x9"));//简介


            }
            rs.close();
            ps.close();

        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return mu;
    }


}