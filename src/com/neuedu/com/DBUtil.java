package com.neuedu.com;

import java.sql.*;

/**
 * Created by ttc on 17-11-16.
 */
public class DBUtil
{
    public static final String ORADRI = "oracle.jdbc.driver.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
    public static Connection conn;

    public static Connection connedDB() throws ClassNotFoundException, SQLException
    {
        //1.��������
        Class.forName(ORADRI);

        //2.����url
        String url = URL;

        //3.��������
        conn = DriverManager.getConnection(url, "scott", "tiger");

        return conn;
    }

    public static void releaseDB(PreparedStatement ps)
    {
        if (conn != null)
        {
            if (ps != null)
            {
                try
                {
                    ps.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }

                try
                {
                    conn.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void releaseDB(PreparedStatement ps, ResultSet rs)
    {
        if (conn != null)
        {
            if (ps != null)
            {
                if (rs != null)
                {
                    try
                    {
                        rs.close();
                    } catch (SQLException e)
                    {
                        e.printStackTrace();
                    }

                    try
                    {
                        ps.close();
                    } catch (SQLException e)
                    {
                        e.printStackTrace();
                    }

                    try
                    {
                        conn.close();
                    } catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
