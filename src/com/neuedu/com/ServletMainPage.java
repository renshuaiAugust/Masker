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
import java.util.ArrayList;
import java.util.List;



/**
 * Created by ttc on 17-12-28.
 */
@WebServlet("/ServletMainPage")
public class ServletMainPage extends HttpServlet   //主页显示动态页面；
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        PreparedStatement ps = null;
        ResultSet rs = null;
        HttpSession hs = request.getSession();  //创建一个Session对象；
        List<Masker_topic> MaskerArr = new ArrayList(); //创建List集合，装话题对象。
        List<String> MaskerArr2 = new ArrayList(); //创建List集合，装被关注人的用户名。



        try
        {
            String Masker_user_x2 = (String) hs.getAttribute("Masker_user_x2"); //获得页面登陆是用户名；

            Connection conn = DBUtil.connedDB();
            String sql = "select * from  masker_user  where Masker_user_x2= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, Masker_user_x2);

            rs = ps.executeQuery();
            if (rs.next())
            {
                //取出用户表中自己的所有信息；

                Masker_user Masker_user = new Masker_user();

                Masker_user.setMasker_user_x1(rs.getInt("Masker_user_x1"));
                Masker_user.setMasker_user_x2(rs.getString("Masker_user_x2"));




                //根据Masker_user_x1取出MASKER_ATTENTION中的被关注人的用户名；
                conn = DBUtil.connedDB();
                sql = "select MASKER_ATTENTION_x2 from  MASKER_ATTENTION where MASKER_ATTENTION_x1 = ? ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, Masker_user.getMasker_user_x1());
                rs = ps.executeQuery();
                while (rs.next())
                {
                    //根据登陆用户名取出对应话题表中所有信息

                    String Masker_user_name1 = rs.getString("MASKER_ATTENTION_x2"); //取出关注表中每个人的用户名；
                    MaskerArr2.add(Masker_user_name1);
                }
                MaskerArr2.add(Masker_user_x2);


                //根据登陆用户名取出对应话题表中所有信息；

                for (int k = 0; k < MaskerArr2.size(); k++)
                {

                    conn = DBUtil.connedDB();
                    sql = "select * from  masker_topic where Masker_topic_x3 = ? ";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, MaskerArr2.get(k));
                    rs = ps.executeQuery();

                    while (rs.next())
                    {
                        Masker_topic Masker_topic = new Masker_topic();


                        Masker_topic.setMasker_topic_x2(rs.getString("masker_topic_x2"));
                        Masker_topic.setMasker_topic_x3(rs.getString("masker_topic_x3"));
                        Masker_topic.setMasker_topic_x4(rs.getString("masker_topic_x4"));
                        Masker_topic.setMasker_topic_x5(rs.getString("masker_topic_x5"));
                        Masker_topic.setMasker_topic_x6(rs.getString("masker_topic_x6"));

                        //将找到的话题添加进集合内

                        MaskerArr.add(Masker_topic);
                    }
                }
            }
            String MaskerArrJSON = JSON.toJSONString(MaskerArr);//只要评论的对象集合。
            response.getWriter().write(MaskerArrJSON);


        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            DBUtil.releaseDB(ps, rs);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
