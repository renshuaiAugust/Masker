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
public class ServletMainPage extends HttpServlet   //��ҳ��ʾ��̬ҳ�棻
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        PreparedStatement ps = null;
        ResultSet rs = null;
        HttpSession hs = request.getSession();  //����һ��Session����
        List<Masker_topic> MaskerArr = new ArrayList(); //����List���ϣ�װ�������
        List<String> MaskerArr2 = new ArrayList(); //����List���ϣ�װ����ע�˵��û�����



        try
        {
            String Masker_user_x2 = (String) hs.getAttribute("Masker_user_x2"); //���ҳ���½���û�����

            Connection conn = DBUtil.connedDB();
            String sql = "select * from  masker_user  where Masker_user_x2= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, Masker_user_x2);

            rs = ps.executeQuery();
            if (rs.next())
            {
                //ȡ���û������Լ���������Ϣ��

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
                    //���ݵ�½�û���ȡ����Ӧ�������������Ϣ

                    String Masker_user_name1 = rs.getString("MASKER_ATTENTION_x2"); //ȡ����ע����ÿ���˵��û�����
                    MaskerArr2.add(Masker_user_name1);
                }
                MaskerArr2.add(Masker_user_x2);


                //���ݵ�½�û���ȡ����Ӧ�������������Ϣ��

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

                        //���ҵ��Ļ�����ӽ�������

                        MaskerArr.add(Masker_topic);
                    }
                }
            }
            String MaskerArrJSON = JSON.toJSONString(MaskerArr);//ֻҪ���۵Ķ��󼯺ϡ�
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
