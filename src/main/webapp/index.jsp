<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>File Upload Example in JSP and Servlet - Java web application</title>

    </head>



    <body>

        <div>

            <h3> Choose File to Upload in Server </h3>
                    

            <form action="upload" method="post" enctype="multipart/form-data">

                <input type="file" name="file" required />

                <input type="submit" value="upload" />

            </form>     
            
            <a href="baixar.jsp">Baixar</a>

        </div>



    </body>

</html>
