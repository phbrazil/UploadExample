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

    //private final String UPLOAD_DIRECTORY = "/opt/tomcat/apache-tomee-webprofile-7.0.2/webapps/files/temp";
    private String UPLOAD_DIRECTORY = "C:/Users/ASAPH-001/Desktop/uploads/temp/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String path = "";

        //process only if its multipart content
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                //gravar na pasta temp
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                        path = UPLOAD_DIRECTORY + File.separator + name;
                    }
                }

                //insert no banco
                insert insert = new insert();
                int id = insert.insert(path);

                //criar pasta com id do banco
                File file = new File("C:/Users/ASAPH-001/Desktop/uploads/" + id+"/");
                //File file = new File("/opt/tomcat/apache-tomee-webprofile-7.0.2/webapps/files/" + id);

                //String novodiretorio = "";
                if (!file.exists()) {
                    if (file.mkdir()) {
                        System.out.println("Directory is created!");
                        // novodiretorio = "/opt/tomcat/apache-tomee-webprofile-7.0.2/webapps/files/" + id;
                    } else {
                        System.out.println("Failed to create directory!");
                    }
                }

                //inserir na nova pasta criada

                UPLOAD_DIRECTORY = "C:/Users/ASAPH-001/Desktop/uploads/" + id+"/";
                //UPLOAD_DIRECTORY = novodiretorio;
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                        System.out.println("inserido");
                    }
                }

                //deletar arquivos na pasta temp
                System.out.println("deletar temp");

                //File tempfolder = new File("/opt/tomcat/apache-tomee-webprofile-7.0.2/webapps/files/temp");
                File tempfolder = new File("C:/Users/ASAPH-001/Desktop/uploads/temp/");
                File[] filestemp = tempfolder.listFiles();

                if (filestemp != null) {
                    for (File f : filestemp) {
                        System.out.println("deletando temp");
                        f.delete();
                    }
                }
                
                UPLOAD_DIRECTORY = null;

                //File uploaded successfully
                request.setAttribute("message", "Upload feito com Sucesso!");

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
