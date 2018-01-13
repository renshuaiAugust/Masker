package com.neuedu.com;

/**
 * Created by ttc on 17-12-27.
 */
public class Masker_user
{
    private Integer Masker_user_x1;  //id
    private String Masker_user_x2;   //用户名
    private String Masker_user_x3;   //密码
    private String Masker_user_x4;   //头像
    private String Masker_user_x5;   //性别
    private String Masker_user_x6;   //关注的人的ID
    private int Masker_user_x7;
    private int Masker_user_x8;
    private String Masker_user_x9;
    private String Masker_user_x10;   //是否关注；


    public Masker_user()
    {
    }


    public Integer getMasker_user_x1()
    {
        return Masker_user_x1;
    }

    public void setMasker_user_x1(Integer masker_user_x1)
    {
        Masker_user_x1 = masker_user_x1;
    }

    public String getMasker_user_x2()
    {
        return Masker_user_x2;
    }

    public void setMasker_user_x2(String masker_user_x2)
    {
        Masker_user_x2 = masker_user_x2;
    }

    public String getMasker_user_x3()
    {
        return Masker_user_x3;
    }

    public void setMasker_user_x3(String masker_user_x3)
    {
        Masker_user_x3 = masker_user_x3;
    }

    public String getMasker_user_x4()
    {
        return Masker_user_x4;
    }

    public void setMasker_user_x4(String masker_user_x4)
    {
        Masker_user_x4 = masker_user_x4;
    }

    public String getMasker_user_x5()
    {
        return Masker_user_x5;
    }

    public void setMasker_user_x5(String masker_user_x5)
    {
        Masker_user_x5 = masker_user_x5;
    }

    public String getMasker_user_x6()
    {
        return Masker_user_x6;
    }

    public void setMasker_user_x6(String masker_user_x6)
    {
        Masker_user_x6 = masker_user_x6;
    }

    public int getMasker_user_x7()
    {
        return Masker_user_x7;
    }

    public void setMasker_user_x7(int masker_user_x7)
    {
        Masker_user_x7 = masker_user_x7;
    }

    public int getMasker_user_x8()
    {
        return Masker_user_x8;
    }

    public void setMasker_user_x8(int masker_user_x8)
    {
        Masker_user_x8 = masker_user_x8;
    }

    public String getMasker_user_x9()
    {
        return Masker_user_x9;
    }

    public void setMasker_user_x9(String masker_user_x9)
    {
        Masker_user_x9 = masker_user_x9;
    }

    public String getMasker_user_x10()
    {
        return Masker_user_x10;
    }

    public void setMasker_user_x10(String masker_user_x10)
    {
        Masker_user_x10 = masker_user_x10;
    }

    @Override
    public String toString()
    {
        return "[" +
                this.Masker_user_x1 + "," + this.Masker_user_x2 + "," + this.Masker_user_x3 +
                "," + this.Masker_user_x4+"," + this.Masker_user_x5+ "," + Masker_user_x6 +
                "," + this.Masker_user_x7 +"," + this.Masker_user_x8+"," + this.Masker_user_x9 +
                "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        Masker_user maskers = (Masker_user) obj;
        if (maskers.Masker_user_x1==this.Masker_user_x1)
        {
            return true;
        }
        return false;
    }
}
