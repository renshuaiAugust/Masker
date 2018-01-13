package com.neuedu.com;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * Created by ttc on 18-1-8.
 */
@WebServlet("/ServletPicturePreview")
@MultipartConfig
public class ServletPicturePreview extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Part part = request.getPart("actionimg");
        String filename = part.getSubmittedFileName();
        String filepath = request.getServletContext().getRealPath("upload");

        String fullpath = filepath + File.separator + filename;
        part.write(fullpath);


        if (filename != "")
        {
            response.getWriter().write("../upload/" + filename);
        } else
        {
            response.getWriter().write("../upload/add.jpg");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
