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
public class ServletSendDynamic extends HttpServlet //����̬��
{


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        //���Session�е��û���

        HttpSession hs = request.getSession();
        String username = (String) hs.getAttribute("Masker_user_x2");


        //���л�ȡ��̬���⣻
        String topic_title = request.getParameter("actiontitle");
        //���л�ȡ��̬���ݣ�
        String topic_Content = request.getParameter("actiondetil");
        //��ȡ����������ļ����ļ�����
        String filepath = request.getServletContext().getRealPath("images");
        //ȡ���ϴ��ļ���
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



                    //�����۱�������ݣ������ΨһIDʹ���������ɡ�
                    conn = connedDB();
                    String sql2 = "insert INTO  masker_topic ( masker_topic_x1 , masker_topic_x2,masker_topic_x3,masker_topic_x4,masker_topic_x5,masker_topic_x6) VALUES (10,?,?,?,?,'')";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setString(1, topic_images);  //�û�ͷ��
                    ps2.setString(2, username);      //�û�����
                    ps2.setString(3, topic_title);   //��̬���⣻
                    ps2.setString(4, topic_Content); //��̬���ݣ�
                         //��̬ͼƬ��

                    int r = ps2.executeUpdate();
                    if (r > 0)
                    {
                        System.out.println("asd");
                        response.getWriter().write("�����ɹ�");

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


                    //�����۱�������ݣ������ΨһIDʹ���������ɡ�
                    conn = connedDB();
                    String sql2 = "insert INTO  masker_topic ( masker_topic_x1 , masker_topic_x2,masker_topic_x3,masker_topic_x4,masker_topic_x5,masker_topic_x6) VALUES (10,?,?,?,?,?)";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setString(1, topic_images);  //�û�ͷ��
                    ps2.setString(2, username);      //�û�����
                    ps2.setString(3, topic_title);   //��̬���⣻
                    ps2.setString(4, topic_Content); //��̬���ݣ�
                    ps2.setString(5, filenames);      //��̬ͼƬ��

                    int r = ps2.executeUpdate();
                    if (r > 0)
                    {
                        String fullpath = filepath + File.separator + filename;

                        part.write(fullpath);

                        response.getWriter().write("�����ɹ�");
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
