package com.mcibrasil.upload;

import com.mcibrasil.upload.dao.insert.insert;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet to handle File upload request from Client
 *
 * @author Javin Paul
 */
public class FileUploadHandler extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "/opt/tomcat/apache-tomee-webprofile-7.0.2/webapps/files";
   // private final String UPLOAD_DIRECTORY = "C:/uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path="";

        //process only if its multipart content
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                        path=UPLOAD_DIRECTORY+File.separator+name;
                    }
                }

                //File uploaded successfully
                request.setAttribute("message", "Upload feito com Sucesso!");

                insert insert = new insert();
                insert.insert(path);

            } catch (Exception ex) {
                request.setAttribute("message", "Upload falhou devido a " + ex);
            }

        } else {
            request.setAttribute("message",
                    "Desculpe, o Servlet aceita somente upload de arquivos");
        }

        request.getRequestDispatcher("/result.jsp").forward(request, response);

    }

}
