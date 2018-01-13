package com.neuedu.com;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by ttc on 18-1-8.
 */
@WebServlet("/ServletHotUser")
public class ServletHotUser extends HttpServlet //热门用户；
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Connection conn = null;
        try
        {
            conn =DBUtil.connedDB();
            String sql = "SELECT  Masker_Attention_x2 FROM masker_attention HAVING  count(Masker_Attention_x2 )>3  GROUP BY Masker_Attention_x2 ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Masker_user> li = new ArrayList();


            int Masker_user_x7 = 0;
            int Masker_user_x8 = 0;

            while (rs.next())
            {
                String username = rs.getString("Masker_Attention_x2");


                conn = DBUtil.connedDB();
                sql = "select * from  masker_user  where Masker_user_x2= ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ResultSet rs2 = ps.executeQuery();

                while (rs2.next())
                {
                    Masker_user Masker_user = new Masker_user();

                    conn = DBUtil.connedDB();
                    sql = "SELECT count(Masker_Attention_x2)FROM masker_attention WHERE Masker_Attention_x2 = ?   GROUP BY Masker_Attention_x2";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, username);
                    ResultSet rs3 = ps.executeQuery();
                    if (rs3.next())
                    {
                        Masker_user_x7 = (rs3.getInt("count(Masker_Attention_x2)"));
                    }
                    conn = DBUtil.connedDB();
                    sql = "SELECT count(masker_topic_x3) FROM masker_topic WHERE masker_topic_x3 =?   GROUP BY masker_topic_x3";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, username);
                    ResultSet rs4 = ps.executeQuery();
                    if (rs4.next())
                    {
                        Masker_user_x8 = rs4.getInt("count(masker_topic_x3)");
                    }

                    Masker_user.setMasker_user_x2(rs2.getString("Masker_user_x7")); //7是昵称
                    Masker_user.setMasker_user_x4(rs2.getString("Masker_user_x4")); //头像
                    Masker_user.setMasker_user_x5(rs2.getString("Masker_user_x5")); //性别
                    Masker_user.setMasker_user_x6(rs2.getString("Masker_user_x6")); //出生年月
                    Masker_user.setMasker_user_x7(Masker_user_x7);                  //粉丝数
                    Masker_user.setMasker_user_x8(Masker_user_x8);                  //动态数
                    Masker_user.setMasker_user_x9(rs2.getString("Masker_user_x9")); //昵称



                    System.out.println(Masker_user);

                    li.add(Masker_user);

                }
            }
            String liJSON= JSON.toJSONString(li);
            System.out.println(liJSON);
            response.getWriter().write(liJSON);


        } catch (
                ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (
                SQLException e)
        {
            e.printStackTrace();
        }

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
