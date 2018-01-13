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
 * Created by ttc on 17-12-27.
 */
@WebServlet("/ServletDemo3")
public class ServletDemo3 extends HttpServlet   //废弃掉的登陆页源代码；
{


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Masker_topic> MaskerArr = new ArrayList(); //创建List集合，装话题对象。
        List<Masker_comment> MaskerArr1 = new ArrayList(); //创建List集合，装评论对象。
        List<String> MaskerArr2 = new ArrayList(); //创建List集合，装被关注人的用户名。
        String Masker_user_Json = null;
        String Masker_topic_Json = null;
        String Masker_comment_Json = null;


        try
        {


//            String Masker_user_x2 = (String) hs.getAttribute("Masker_user_x2"); //获得页面登陆是用户名；


            String Masker_user_x2 = request.getParameter("username"); //获得页面登陆是用户名；




            Connection conn = DBUtil.connedDB();
            String sql = "select * from  masker_user  where Masker_user_x2= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, Masker_user_x2);

            rs = ps.executeQuery();
            if (rs.next())  //根据有户名将登录人信息取出；
            {
                //取出用户表中的自己

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

                    String Masker_user_name1 = rs.getString("MASKER_ATTENTION_x2"); //取出关注表中每个人的用户名；
                    MaskerArr2.add(Masker_user_name1);
                }

                MaskerArr2.add(Masker_user_x2);

                //根据登陆用户名取出对应话题表中所有信息；

                for (int k = 0; k < MaskerArr2.size(); k++)
                {
                    String Masker_user_name2 = MaskerArr2.get(k);

                    conn = DBUtil.connedDB();
                    sql = "select * from  masker_topic where Masker_topic_x2 = ? ";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, Masker_user_name2);
                    rs = ps.executeQuery();

                    while (rs.next())
                    {
                        Masker_topic Masker_topic = new Masker_topic();

                        Masker_topic.setMasker_topic_x1(rs.getInt("masker_topic_x1"));
                        Masker_topic.setMasker_topic_x2(rs.getString("masker_topic_x2"));
                        Masker_topic.setMasker_topic_x3(rs.getString("masker_topic_x3"));
                        Masker_topic.setMasker_topic_x4(rs.getString("masker_topic_x4"));

                        MaskerArr.add(Masker_topic); //将找到的话题添加进集合内

                    }

                }


                if (MaskerArr != null && !MaskerArr.isEmpty())
                {
                    Masker_topic_Json = JSON.toJSONString(MaskerArr); //将集合转换成JSON字符串；

                    for (int i = 0; i < MaskerArr.size(); i++)
                    {

                        //将masker_topic表中每个问题的唯一ID取出，使用ID在评论表中取出评论；

                        int Masker_topic_ID = MaskerArr.get(i).getMasker_topic_x1();

                        //Masker_topic_ID是问题唯一的，把评论表中ID和这个ID一致的取出来；

                        conn =DBUtil.connedDB();

                        sql = "select * from  Masker_comment where Masker_comment_x1 = ?";

                        ps = conn.prepareStatement(sql);

                        ps.setInt(1, Masker_topic_ID);

                        rs = ps.executeQuery();

                        while (rs.next())
                        {

                            Masker_comment Masker_comment = new Masker_comment();

                            Masker_comment.setMasker_comment_x2(rs.getString("Masker_comment_x2"));
                            Masker_comment.setMasker_comment_x3(rs.getString("Masker_comment_x3"));
                            Masker_comment.setMasker_comment_x4(rs.getString("Masker_comment_x4"));
                            Masker_comment.setMasker_comment_x5(rs.getString("Masker_comment_x5"));
                            Masker_comment.setMasker_comment_x6(rs.getString("Masker_comment_x6"));
                            Masker_comment.setMasker_comment_x7(rs.getString("Masker_comment_x7"));
                            Masker_comment.setMasker_comment_x8(rs.getString("Masker_comment_x8"));

                            MaskerArr1.add(Masker_comment);//将找到的评论添加进集合内
                        }
                    }
                    Masker_comment_Json = JSON.toJSONString(MaskerArr1); //将集合转换成JSON字符串；
                }
            }
            //将Masker_user_Json对象，Masker_topic_Json集合，Masker_comment_Json集合，写回JSP。
            response.getWriter().write(Masker_user_Json + Masker_topic_Json + Masker_comment_Json);

//            System.out.println(Masker_user_Json + Masker_topic_Json + Masker_comment_Json);


        }





        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBUtil.releaseDB(ps, rs);
        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
