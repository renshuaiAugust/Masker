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
public class ServletDemo3 extends HttpServlet   //�������ĵ�½ҳԴ���룻
{


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Masker_topic> MaskerArr = new ArrayList(); //����List���ϣ�װ�������
        List<Masker_comment> MaskerArr1 = new ArrayList(); //����List���ϣ�װ���۶���
        List<String> MaskerArr2 = new ArrayList(); //����List���ϣ�װ����ע�˵��û�����
        String Masker_user_Json = null;
        String Masker_topic_Json = null;
        String Masker_comment_Json = null;


        try
        {


//            String Masker_user_x2 = (String) hs.getAttribute("Masker_user_x2"); //���ҳ���½���û�����


            String Masker_user_x2 = request.getParameter("username"); //���ҳ���½���û�����




            Connection conn = DBUtil.connedDB();
            String sql = "select * from  masker_user  where Masker_user_x2= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, Masker_user_x2);

            rs = ps.executeQuery();
            if (rs.next())  //�����л�������¼����Ϣȡ����
            {
                //ȡ���û����е��Լ�

                Masker_user Masker_user = new Masker_user();

                Masker_user.setMasker_user_x1(rs.getInt("Masker_user_x1"));
                Masker_user.setMasker_user_x2(rs.getString("Masker_user_x2"));




                //����Masker_user_x1ȡ��MASKER_ATTENTION�еı���ע�˵��û�����
                conn = DBUtil.connedDB();
                sql = "select MASKER_ATTENTION_x2 from  MASKER_ATTENTION where MASKER_ATTENTION_x1 = ? ";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, Masker_user.getMasker_user_x1());
                rs = ps.executeQuery();
                while (rs.next())
                {

                    String Masker_user_name1 = rs.getString("MASKER_ATTENTION_x2"); //ȡ����ע����ÿ���˵��û�����
                    MaskerArr2.add(Masker_user_name1);
                }

                MaskerArr2.add(Masker_user_x2);

                //���ݵ�½�û���ȡ����Ӧ�������������Ϣ��

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

                        MaskerArr.add(Masker_topic); //���ҵ��Ļ�����ӽ�������

                    }

                }


                if (MaskerArr != null && !MaskerArr.isEmpty())
                {
                    Masker_topic_Json = JSON.toJSONString(MaskerArr); //������ת����JSON�ַ�����

                    for (int i = 0; i < MaskerArr.size(); i++)
                    {

                        //��masker_topic����ÿ�������ΨһIDȡ����ʹ��ID�����۱���ȡ�����ۣ�

                        int Masker_topic_ID = MaskerArr.get(i).getMasker_topic_x1();

                        //Masker_topic_ID������Ψһ�ģ������۱���ID�����IDһ�µ�ȡ������

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

                            MaskerArr1.add(Masker_comment);//���ҵ���������ӽ�������
                        }
                    }
                    Masker_comment_Json = JSON.toJSONString(MaskerArr1); //������ת����JSON�ַ�����
                }
            }
            //��Masker_user_Json����Masker_topic_Json���ϣ�Masker_comment_Json���ϣ�д��JSP��
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
