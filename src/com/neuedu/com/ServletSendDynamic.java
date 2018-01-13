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

import static com.neuedu.com.DBUtil.connedDB;


/**
 * Created by ttc on 18-1-3.
 */
@WebServlet("/ServletSendDynamic")
@MultipartConfig
public class ServletSendDynamic extends HttpServlet //发动态；
{


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        //获得Session中的用户名

        HttpSession hs = request.getSession();
        String username = (String) hs.getAttribute("Masker_user_x2");


        //表单中获取动态标题；
        String topic_title = request.getParameter("actiontitle");
        //表单中获取动态内容；
        String topic_Content = request.getParameter("actiondetil");
        //获取服务器存放文件的文件名；
        String filepath = request.getServletContext().getRealPath("images");
        //取出上传文件名
        Part part = request.getPart("actionimg");

        String filename = part.getSubmittedFileName();


        if (filename.equals(""))
        {
            try
            {
                Connection conn = connedDB();
                String sql1 = "select masker_user_x4 from masker_user where masker_user_x2 = ? ";
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setString(1, username);
                ResultSet rs = ps1.executeQuery();
                if (rs.next())
                {
                    String topic_images = rs.getString("masker_user_x4");



                    //向评论表添加数据；问题的唯一ID使用序列生成。
                    conn = connedDB();
                    String sql2 = "insert INTO  masker_topic ( masker_topic_x1 , masker_topic_x2,masker_topic_x3,masker_topic_x4,masker_topic_x5,masker_topic_x6) VALUES (10,?,?,?,?,'')";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setString(1, topic_images);  //用户头像；
                    ps2.setString(2, username);      //用户名；
                    ps2.setString(3, topic_title);   //动态标题；
                    ps2.setString(4, topic_Content); //动态内容；
                         //动态图片；

                    int r = ps2.executeUpdate();
                    if (r > 0)
                    {
                        System.out.println("asd");
                        response.getWriter().write("发布成功");

                    }
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        } else
        {
            try
            {
                Connection conn = connedDB();
                String sql1 = "select masker_user_x4 from masker_user where masker_user_x2 = ? ";
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setString(1, username);
                ResultSet rs = ps1.executeQuery();
                if (rs.next())
                {
                    String topic_images = rs.getString("masker_user_x4");
                    System.out.println(topic_images);
                    String filenames = "/images/"+filename;


                    //向评论表添加数据；问题的唯一ID使用序列生成。
                    conn = connedDB();
                    String sql2 = "insert INTO  masker_topic ( masker_topic_x1 , masker_topic_x2,masker_topic_x3,masker_topic_x4,masker_topic_x5,masker_topic_x6) VALUES (10,?,?,?,?,?)";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setString(1, topic_images);  //用户头像；
                    ps2.setString(2, username);      //用户名；
                    ps2.setString(3, topic_title);   //动态标题；
                    ps2.setString(4, topic_Content); //动态内容；
                    ps2.setString(5, filenames);      //动态图片；

                    int r = ps2.executeUpdate();
                    if (r > 0)
                    {
                        String fullpath = filepath + File.separator + filename;

                        part.write(fullpath);

                        response.getWriter().write("发布成功");
                    }
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException
    {
        doPost(request, response);
    }
}
